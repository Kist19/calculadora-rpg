package com.arthur.calculadorarpg.inventario;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arthur.calculadorarpg.arma.Arma;
import com.arthur.calculadorarpg.arma.ArmaRepository;
import com.arthur.calculadorarpg.armadura.Armadura;
import com.arthur.calculadorarpg.armadura.ArmaduraRepository;
import com.arthur.calculadorarpg.efeito.Efeito;
import com.arthur.calculadorarpg.efeitoativo.EfeitoAtivo;
import com.arthur.calculadorarpg.efeitoativo.EfeitoAtivoRepository;
import com.arthur.calculadorarpg.item.Item;
import com.arthur.calculadorarpg.item.ItemRepository;
import com.arthur.calculadorarpg.personagem.Personagem;
import com.arthur.calculadorarpg.personagem.PersonagemRepository;
import com.arthur.calculadorarpg.status.Status;

@Service
public class InventarioService {

    private final InventarioRepository inventarioRepository;
    private final EfeitoAtivoRepository efeitoAtivoRepository;
    private final PersonagemRepository personagemRepository;
    private final ItemRepository itemRepository;
    private final ArmaRepository armaRepository;
    private final ArmaduraRepository armaduraRepository;

    public InventarioService(
            InventarioRepository inventarioRepository,
            EfeitoAtivoRepository efeitoAtivoRepository,
            PersonagemRepository personagemRepository,
            ItemRepository itemRepository,
            ArmaRepository armaRepository,
            ArmaduraRepository armaduraRepository) {
        this.inventarioRepository = inventarioRepository;
        this.efeitoAtivoRepository = efeitoAtivoRepository;
        this.personagemRepository = personagemRepository;
        this.itemRepository = itemRepository;
        this.armaRepository = armaRepository;
        this.armaduraRepository = armaduraRepository;
    }

    public List<Inventario> listarInventario(Long personagemId) {
        return inventarioRepository.findByPersonagemId(personagemId)
                .stream()
                .filter(i -> i.getItem() != null
                        && i.getInventarioQuantidade() != null
                        && i.getInventarioQuantidade() > 0)
                .toList();
    }

    public Map<String, Object> verInventarioCompleto(Long personagemId) {
        List<Inventario> itens = inventarioRepository.findByPersonagemId(personagemId)
                .stream()
                .filter(i -> i.getItem() != null && i.getInventarioQuantidade() != null
                        && i.getInventarioQuantidade() > 0)
                .toList();

        List<Arma> armas = armaRepository.findByInventarioPersonagemId(personagemId);
        List<Armadura> armaduras = armaduraRepository.findByInventarioPersonagemId(personagemId);

        Map<String, Object> inventario = new HashMap<>();
        inventario.put("itens", itens);
        inventario.put("armas", armas);
        inventario.put("armaduras", armaduras);

        return inventario;
    }

    @Transactional
    public Map<String, Object> usarItem(Long inventarioId) {
        Inventario inventario = inventarioRepository.findById(inventarioId)
                .orElseThrow(() -> new RuntimeException("Inventário não encontrado"));

        Item item = inventario.getItem();
        if (item == null) {
            throw new RuntimeException("Item não encontrado no inventário");
        }

        Personagem personagem = inventario.getPersonagem();
        if (personagem == null) {
            throw new RuntimeException("Personagem não encontrado no inventário");
        }

        Status status = personagem.getStatus();
        if (status == null) {
            throw new RuntimeException("Status do personagem não encontrado");
        }

        Integer duracao = item.getItemDuracaoTurno() != null ? item.getItemDuracaoTurno() : 0;
        personagem.getItensTurnosRestantesNaCena().put(item.getItemNome(), duracao);
        personagemRepository.save(personagem);

        int vidaAtual = status.getStatusVidaAtual() != null ? status.getStatusVidaAtual() : 0;
        int manaAtual = status.getStatusManaAtual() != null ? status.getStatusManaAtual() : 0;
        int vidaBase = status.getStatusVidaBase() != null ? status.getStatusVidaBase() : Integer.MAX_VALUE;
        int manaBase = status.getStatusManaBase() != null ? status.getStatusManaBase() : Integer.MAX_VALUE;

        vidaAtual += item.getItemBonusPv() != null ? item.getItemBonusPv() : 0;
        vidaAtual -= item.getItemPenalidadePv() != null ? item.getItemPenalidadePv() : 0;

        manaAtual += item.getItemBonusPm() != null ? item.getItemBonusPm() : 0;
        manaAtual -= item.getItemPenalidadePm() != null ? item.getItemPenalidadePm() : 0;

        if (vidaAtual < 0)
            vidaAtual = 0;
        if (manaAtual < 0)
            manaAtual = 0;
        if (vidaAtual > vidaBase)
            vidaAtual = vidaBase;
        if (manaAtual > manaBase)
            manaAtual = manaBase;

        status.setStatusVidaAtual(vidaAtual);
        status.setStatusManaAtual(manaAtual);

        if (item.getEfeito() != null) {
            criarEfeitoAtivoDoItem(inventario);
        }

        Integer quantidadeAtual = inventario.getInventarioQuantidade() != null
                ? inventario.getInventarioQuantidade()
                : 0;

        if (quantidadeAtual > 1) {
            inventario.setInventarioQuantidade(quantidadeAtual - 1);
        } else {
            inventario.setItem(null);
            inventario.setInventarioQuantidade(0);
        }

        inventarioRepository.save(inventario);
        recalcularEspacoAtual(personagem.getId());

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("mensagem", "Item usado com sucesso");
        resposta.put("personagemId", personagem.getId());
        resposta.put("personagemNome", personagem.getPersonagemNome());
        resposta.put("itemUsado", item.getItemNome());
        resposta.put("vidaAtual", status.getStatusVidaAtual());
        resposta.put("manaAtual", status.getStatusManaAtual());
        resposta.put("itensTurnosRestantesNaCena", personagem.getItensTurnosRestantesNaCena());

        return resposta;
    }

    private void criarEfeitoAtivoDoItem(Inventario inventario) {
        Item item = inventario.getItem();
        Personagem personagem = inventario.getPersonagem();
        Efeito efeito = item.getEfeito();

        if (personagem == null) {
            throw new RuntimeException("Personagem não encontrado no inventário");
        }

        if (efeito == null) {
            throw new RuntimeException("Esse item não possui efeito vinculado");
        }

        EfeitoAtivo efeitoAtivoExistente = efeitoAtivoRepository
                .findByPersonagemIdAndEfeitoId(personagem.getId(), efeito.getId())
                .orElse(null);

        if (efeitoAtivoExistente != null) {
            efeitoAtivoExistente.setEfeitoAtivoTurnoRestante(efeito.getEfeitoDuracaoTurno());
            efeitoAtivoExistente.setEfeitoAtivoLigado(true);
            efeitoAtivoRepository.save(efeitoAtivoExistente);
            return;
        }

        EfeitoAtivo novoEfeitoAtivo = new EfeitoAtivo();
        novoEfeitoAtivo.setPersonagem(personagem);
        novoEfeitoAtivo.setEfeito(efeito);
        novoEfeitoAtivo.setEfeitoAtivoTurnoRestante(efeito.getEfeitoDuracaoTurno());
        novoEfeitoAtivo.setEfeitoAtivoLigado(true);

        efeitoAtivoRepository.save(novoEfeitoAtivo);
    }

    public Inventario adicionarItemAoPersonagem(Long personagemId, Long itemId, Integer quantidade) {
        Personagem personagem = personagemRepository.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        Inventario inventario = inventarioRepository.findByPersonagemIdAndItemId(personagemId, itemId)
                .orElse(null);

        if (inventario != null) {
            int quantidadeAtual = inventario.getInventarioQuantidade() != null
                    ? inventario.getInventarioQuantidade()
                    : 0;

            inventario.setInventarioQuantidade(quantidadeAtual + quantidade);
            Inventario salvo = inventarioRepository.save(inventario);
            recalcularEspacoAtual(personagemId);
            return salvo;
        }

        Inventario novoInventario = new Inventario();
        novoInventario.setPersonagem(personagem);
        novoInventario.setItem(item);
        novoInventario.setInventarioQuantidade(quantidade);

        Inventario salvo = inventarioRepository.save(novoInventario);
        recalcularEspacoAtual(personagemId);
        return salvo;
    }

    public void removerItemDoInventario(Long inventarioId, Long itemId, Integer quantidade) {
        Inventario inventario = inventarioRepository.findById(inventarioId)
                .orElseThrow(() -> new RuntimeException("Registro de inventário não encontrado"));

        if (inventario.getItem() == null || !inventario.getItem().getId().equals(itemId)) {
            throw new RuntimeException("Item não encontrado nesse registro de inventário");
        }

        if (quantidade == null || quantidade <= 0) {
            throw new RuntimeException("A quantidade deve ser maior que 0");
        }

        if (inventario.getInventarioQuantidade() < quantidade) {
            throw new RuntimeException("Quantidade insuficiente no inventário para remoção");
        }

        int novaQuantidade = inventario.getInventarioQuantidade() - quantidade;
        Long personagemId = inventario.getPersonagem().getId();

        if (novaQuantidade > 0) {
            inventario.setInventarioQuantidade(novaQuantidade);
        } else {
            inventario.setItem(null);
            inventario.setInventarioQuantidade(0);
        }

        inventarioRepository.save(inventario);
        recalcularEspacoAtual(personagemId);
    }

    private void recalcularEspacoAtual(Long personagemId) {
        Personagem personagem = personagemRepository.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        Status status = personagem.getStatus();
        if (status == null) {
            throw new RuntimeException("Status do personagem não encontrado");
        }

        double totalEspaco = 0.0;

        List<Inventario> itens = inventarioRepository.findByPersonagemId(personagemId)
                .stream()
                .filter(i -> i.getItem() != null
                        && i.getInventarioQuantidade() != null
                        && i.getInventarioQuantidade() > 0)
                .toList();

        for (Inventario inventario : itens) {
            Double itemEspaco = inventario.getItem().getItemEspaco() != null
                    ? inventario.getItem().getItemEspaco()
                    : 0.0;

            int quantidade = inventario.getInventarioQuantidade() != null
                    ? inventario.getInventarioQuantidade()
                    : 0;

            totalEspaco += itemEspaco * quantidade;
        }

        List<Arma> armas = armaRepository.findByInventarioPersonagemId(personagemId);
        for (Arma arma : armas) {
            totalEspaco += arma.getArmaEspaco();
        }

        List<Armadura> armaduras = armaduraRepository.findByInventarioPersonagemId(personagemId);
        for (Armadura armadura : armaduras) {
            totalEspaco += armadura.getArmaduraEspaco();
        }

        status.setStatusEspacoAtual(totalEspaco);
        personagemRepository.save(personagem);
    }

    public Inventario buscarOuCriarInventarioBase(Long personagemId) {
        Personagem personagem = personagemRepository.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        return inventarioRepository.findFirstByPersonagemIdAndItemIsNull(personagemId)
                .orElseGet(() -> {
                    Inventario inventarioBase = new Inventario();
                    inventarioBase.setPersonagem(personagem);
                    inventarioBase.setItem(null);
                    inventarioBase.setInventarioQuantidade(0);
                    return inventarioRepository.save(inventarioBase);
                });
    }

    public InventarioBaseResponseDTO buscarInventarioBaseDTO(Long personagemId) {
        Inventario inventario = buscarOuCriarInventarioBase(personagemId);

        return new InventarioBaseResponseDTO(
                inventario.getId(),
                inventario.getPersonagem().getId(),
                inventario.getPersonagem().getPersonagemNome());
    }

    public void removerArmaDoInventario(Long inventarioId, Long armaId) {
        Arma arma = armaRepository.findById(armaId)
                .orElseThrow(() -> new RuntimeException("Arma não encontrada"));

        if (arma.getInventario() == null || !arma.getInventario().getId().equals(inventarioId)) {
            throw new RuntimeException("Arma não encontrada nesse inventário");
        }

        Long personagemId = arma.getInventario().getPersonagem().getId();

        Personagem personagem = personagemRepository.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        if (personagem.getArmaEquipada() != null
                && personagem.getArmaEquipada().getId().equals(armaId)) {
            throw new RuntimeException("Arma equipada. Desequipe primeiro.");
        }

        arma.setInventario(null);
        armaRepository.save(arma);

        recalcularEspacoAtual(personagemId);
    }

    public void removerArmaduraDoInventario(Long inventarioId, Long armaduraId) {
        Armadura armadura = armaduraRepository.findById(armaduraId)
                .orElseThrow(() -> new RuntimeException("Armadura não encontrada"));

        if (armadura.getInventario() == null || !armadura.getInventario().getId().equals(inventarioId)) {
            throw new RuntimeException("Armadura não encontrada nesse inventário");
        }

        Long personagemId = armadura.getInventario().getPersonagem().getId();

        Personagem personagem = personagemRepository.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        boolean armaduraEstaEquipada = personagem.getArmaduraEquipada() != null
                && personagem.getArmaduraEquipada().getId().equals(armaduraId);

        boolean escudoEstaEquipado = personagem.getEscudoEquipado() != null
                && personagem.getEscudoEquipado().getId().equals(armaduraId);

        if (armaduraEstaEquipada || escudoEstaEquipado) {
            throw new RuntimeException("Armadura equipada. Desequipe primeiro.");
        }

        armadura.setInventario(null);
        armaduraRepository.save(armadura);

        recalcularEspacoAtual(personagemId);
    }
}