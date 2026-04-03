package com.arthur.calculadorarpg.loja;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arthur.calculadorarpg.armadura.Armadura;
import com.arthur.calculadorarpg.armadura.ArmaduraRepository;
import com.arthur.calculadorarpg.arma.Arma;
import com.arthur.calculadorarpg.arma.ArmaRepository;
import com.arthur.calculadorarpg.inventario.Inventario;
import com.arthur.calculadorarpg.inventario.InventarioRepository;
import com.arthur.calculadorarpg.personagem.Personagem;
import com.arthur.calculadorarpg.personagem.PersonagemRepository;
import com.arthur.calculadorarpg.status.Status;
import com.arthur.calculadorarpg.status.StatusRepository;

@Service
public class LojaService {

    private final LojaRepository lojaRepository;
    private final LojaItemRepository lojaItemRepository;
    private final PersonagemRepository personagemRepository;
    private final InventarioRepository inventarioRepository;
    private final StatusRepository statusRepository;
    private final ArmaRepository armaRepository;
    private final ArmaduraRepository armaduraRepository;

    public LojaService(
            LojaRepository lojaRepository,
            LojaItemRepository lojaItemRepository,
            PersonagemRepository personagemRepository,
            InventarioRepository inventarioRepository,
            StatusRepository statusRepository,
            ArmaRepository armaRepository,
            ArmaduraRepository armaduraRepository) {

        this.lojaRepository = lojaRepository;
        this.lojaItemRepository = lojaItemRepository;
        this.personagemRepository = personagemRepository;
        this.inventarioRepository = inventarioRepository;
        this.statusRepository = statusRepository;
        this.armaRepository = armaRepository;
        this.armaduraRepository = armaduraRepository;
    }

    @Transactional
    public Loja criarLoja(Loja loja) {
        if (loja.getAtiva() == null) {
            loja.setAtiva(true);
        }

        if (Boolean.TRUE.equals(loja.getAtiva())) {
            desativarTodasAsLojas();
        }

        return lojaRepository.save(loja);
    }

    public List<Loja> listarLojas() {
        return lojaRepository.findAll();
    }

    public List<Loja> listarLojasAtivas() {
        return lojaRepository.findByAtivaTrue();
    }

    public Optional<Loja> buscarLojaAtiva() {
        return lojaRepository.findFirstByAtivaTrue();
    }

    @Transactional
    public Loja ativarLoja(Long lojaId) {
        Loja loja = lojaRepository.findById(lojaId)
                .orElseThrow(() -> new RuntimeException("Loja não encontrada"));

        desativarTodasAsLojas();
        loja.setAtiva(true);

        return lojaRepository.save(loja);
    }

    @Transactional
    public Loja atualizarLoja(Long id, Loja dadosAtualizados) {
        Loja loja = lojaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loja não encontrada"));

        if (dadosAtualizados.getLojaNome() != null) {
            loja.setLojaNome(dadosAtualizados.getLojaNome());
        }

        if (dadosAtualizados.getLojaTipo() != null) {
            loja.setLojaTipo(dadosAtualizados.getLojaTipo());
        }

        if (dadosAtualizados.getAtiva() != null) {
            if (Boolean.TRUE.equals(dadosAtualizados.getAtiva())) {
                desativarTodasAsLojas();
                loja.setAtiva(true);
            } else {
                loja.setAtiva(false);
            }
        }

        return lojaRepository.save(loja);
    }

    public void deletarLoja(Long id) {
        Loja loja = lojaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loja não encontrada"));

        lojaRepository.delete(loja);
    }

    public Loja buscarPorId(Long lojaId) {
        return lojaRepository.findById(lojaId)
                .orElseThrow(() -> new RuntimeException("Loja não encontrada"));
    }

    public LojaItem buscarLojaItemPorId(Long lojaItemId) {
        return lojaItemRepository.findById(lojaItemId)
                .orElseThrow(() -> new RuntimeException("Item da loja não encontrado"));
    }

    public LojaItem adicionarProdutoNaLoja(Long lojaId, LojaItem lojaItem) {
        Loja loja = lojaRepository.findById(lojaId)
                .orElseThrow(() -> new RuntimeException("Loja não encontrada"));

        validarProdutoLoja(lojaItem);

        lojaItem.setLoja(loja);
        return lojaItemRepository.save(lojaItem);
    }

    public LojaItem atualizarLojaItem(Long id, LojaItem dadosAtualizados) {
        LojaItem lojaItem = lojaItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item da loja não encontrado"));

        if (dadosAtualizados.getLojaPreco() != null) {
            lojaItem.setLojaPreco(dadosAtualizados.getLojaPreco());
        }

        if (dadosAtualizados.getLojaQuantidade() != null) {
            lojaItem.setLojaQuantidade(dadosAtualizados.getLojaQuantidade());
        }

        if (dadosAtualizados.getDisponivel() != null) {
            lojaItem.setDisponivel(dadosAtualizados.getDisponivel());
        }

        return lojaItemRepository.save(lojaItem);
    }

    public void deletarLojaItem(Long id) {
        LojaItem lojaItem = lojaItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item da loja não encontrado"));

        lojaItemRepository.delete(lojaItem);
    }

    public String comprarItem(Long personagemId, Long lojaItemId) {
        Optional<Personagem> personagemOpt = personagemRepository.findById(personagemId);
        Optional<LojaItem> lojaItemOpt = lojaItemRepository.findById(lojaItemId);

        if (personagemOpt.isEmpty()) {
            return "Personagem não encontrado.";
        }

        if (lojaItemOpt.isEmpty()) {
            return "Item da loja não encontrado.";
        }

        Personagem personagem = personagemOpt.get();
        LojaItem lojaItem = lojaItemOpt.get();

        if (lojaItem.getLoja() == null || Boolean.FALSE.equals(lojaItem.getLoja().getAtiva())) {
            return "A loja deste produto não está ativa.";
        }

        if (Boolean.FALSE.equals(lojaItem.getDisponivel())) {
            return "Este item não está disponível para compra.";
        }

        if (lojaItem.getLojaQuantidade() == null || lojaItem.getLojaQuantidade() <= 0) {
            return "Item sem estoque na loja.";
        }

        if (lojaItem.getArma() != null && personagemJaPossuiArma(personagemId, lojaItem.getArma())) {
            return "Você já possui esta arma.";
        }

        if (lojaItem.getArmadura() != null && personagemJaPossuiArmadura(personagemId, lojaItem.getArmadura())) {
            return "Você já possui esta armadura.";
        }

        Status status = personagem.getStatus();
        if (status == null) {
            return "O personagem não possui status cadastrado.";
        }

        Integer dinheiroAtual = status.getStatusDinheiro() != null ? status.getStatusDinheiro() : 0;
        Integer precoItem = lojaItem.getLojaPreco() != null ? lojaItem.getLojaPreco() : 0;

        if (dinheiroAtual < precoItem) {
            return "Dinheiro insuficiente.";
        }

        status.setStatusDinheiro(dinheiroAtual - precoItem);
        statusRepository.save(status);

        lojaItem.setLojaQuantidade(lojaItem.getLojaQuantidade() - 1);
        if (lojaItem.getLojaQuantidade() <= 0) {
            lojaItem.setDisponivel(false);
        }
        lojaItemRepository.save(lojaItem);

        if (lojaItem.getItem() != null) {
            adicionarItemAoInventario(personagem, lojaItem.getItem());
            return "Compra de item realizada com sucesso.";
        }

        if (lojaItem.getArma() != null) {
            Inventario inventarioBase = buscarOuCriarInventarioBase(personagem);
            adicionarArmaAoInventario(inventarioBase, lojaItem.getArma());
            return "Compra de arma realizada com sucesso.";
        }

        if (lojaItem.getArmadura() != null) {
            Inventario inventarioBase = buscarOuCriarInventarioBase(personagem);
            adicionarArmaduraAoInventario(inventarioBase, lojaItem.getArmadura());
            return "Compra de armadura realizada com sucesso.";
        }

        return "Produto da loja inválido.";
    }

    private void adicionarItemAoInventario(Personagem personagem, com.arthur.calculadorarpg.item.Item item) {
        Inventario inventarioExistente = inventarioRepository
                .findByPersonagemIdAndItemId(personagem.getId(), item.getId())
                .orElse(null);

        if (inventarioExistente != null) {
            Integer quantidadeAtual = inventarioExistente.getInventarioQuantidade() != null
                    ? inventarioExistente.getInventarioQuantidade()
                    : 0;

            inventarioExistente.setInventarioQuantidade(quantidadeAtual + 1);
            inventarioRepository.save(inventarioExistente);
        } else {
            Inventario novoInventario = new Inventario();
            novoInventario.setPersonagem(personagem);
            novoInventario.setItem(item);
            novoInventario.setInventarioQuantidade(1);
            inventarioRepository.save(novoInventario);
        }
    }

    private void adicionarArmaAoInventario(Inventario inventarioBase, Arma armaBase) {
        Arma novaArma = new Arma();

        novaArma.setInventario(inventarioBase);
        novaArma.setArmaNome(armaBase.getArmaNome());
        novaArma.setArmaTipoPericia(armaBase.getArmaTipoPericia());
        novaArma.setArmaTesteAtaque(armaBase.getArmaTesteAtaque());
        novaArma.setArmaBonusTesteAtaque(armaBase.getArmaBonusTesteAtaque());
        novaArma.setArmaPenalidadeTesteAtaque(armaBase.getArmaPenalidadeTesteAtaque());
        novaArma.setArmaQuantidadeDado(armaBase.getArmaQuantidadeDado());
        novaArma.setArmaTipoDado(armaBase.getArmaTipoDado());
        novaArma.setArmaBonusDano(armaBase.getArmaBonusDano());
        novaArma.setArmaPenalidadeDano(armaBase.getArmaPenalidadeDano());
        novaArma.setArmaCriticoMinimo(armaBase.getArmaCriticoMinimo());
        novaArma.setArmaCriticoMultiplicador(armaBase.getArmaCriticoMultiplicador());
        novaArma.setArmaAlcance(armaBase.getArmaAlcance());
        novaArma.setArmaTipo(armaBase.getArmaTipo());
        novaArma.setArmaEspaco(armaBase.getArmaEspaco());
        novaArma.setArmaPreco(armaBase.getArmaPreco());
        novaArma.setArmaBonusPv(armaBase.getArmaBonusPv());
        novaArma.setArmaPenalidadePv(armaBase.getArmaPenalidadePv());
        novaArma.setArmaBonusPm(armaBase.getArmaBonusPm());
        novaArma.setArmaPenalidadePm(armaBase.getArmaPenalidadePm());

        armaRepository.save(novaArma);
    }

    private void adicionarArmaduraAoInventario(Inventario inventarioBase, Armadura armaduraBase) {
        Armadura novaArmadura = new Armadura();

        novaArmadura.setInventario(inventarioBase);
        novaArmadura.setArmaduraNome(armaduraBase.getArmaduraNome());
        novaArmadura.setArmaduraPreco(armaduraBase.getArmaduraPreco());
        novaArmadura.setArmaduraBonusDefesa(armaduraBase.getArmaduraBonusDefesa());
        novaArmadura.setArmaduraPenalidadeArmadura(armaduraBase.getArmaduraPenalidadeArmadura());
        novaArmadura.setArmaduraEspaco(armaduraBase.getArmaduraEspaco());
        novaArmadura.setArmaduraTipo(armaduraBase.getArmaduraTipo());

        armaduraRepository.save(novaArmadura);
    }

    private Inventario buscarOuCriarInventarioBase(Personagem personagem) {
        return inventarioRepository.findFirstByPersonagemIdAndItemIsNull(personagem.getId())
                .orElseGet(() -> {
                    Inventario novoInventarioBase = new Inventario();
                    novoInventarioBase.setPersonagem(personagem);
                    novoInventarioBase.setInventarioQuantidade(0);
                    return inventarioRepository.save(novoInventarioBase);
                });
    }

    private boolean personagemJaPossuiArma(Long personagemId, Arma arma) {
        if (arma == null || arma.getArmaNome() == null || arma.getArmaNome().isBlank()) {
            return false;
        }

        return armaRepository.existsByInventarioPersonagemIdAndArmaNomeIgnoreCase(
                personagemId,
                arma.getArmaNome().trim());
    }

    private boolean personagemJaPossuiArmadura(Long personagemId, Armadura armadura) {
        if (armadura == null || armadura.getArmaduraNome() == null || armadura.getArmaduraNome().isBlank()) {
            return false;
        }

        return armaduraRepository.existsByInventarioPersonagemIdAndArmaduraNomeIgnoreCase(
                personagemId,
                armadura.getArmaduraNome().trim());
    }

    private void validarProdutoLoja(LojaItem lojaItem) {
        int quantidadePreenchida = 0;

        if (lojaItem.getItem() != null) {
            quantidadePreenchida++;
        }

        if (lojaItem.getArma() != null) {
            quantidadePreenchida++;
        }

        if (lojaItem.getArmadura() != null) {
            quantidadePreenchida++;
        }

        if (quantidadePreenchida == 0) {
            throw new RuntimeException("Informe item, arma ou armadura para adicionar na loja.");
        }

        if (quantidadePreenchida > 1) {
            throw new RuntimeException("A loja só pode receber um tipo por registro: item, arma ou armadura.");
        }
    }

    private void desativarTodasAsLojas() {
        List<Loja> lojas = lojaRepository.findAll();

        for (Loja loja : lojas) {
            if (Boolean.TRUE.equals(loja.getAtiva())) {
                loja.setAtiva(false);
            }
        }

        lojaRepository.saveAll(lojas);
    }
}