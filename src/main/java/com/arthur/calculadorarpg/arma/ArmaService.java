package com.arthur.calculadorarpg.arma;

import java.util.List;

import org.springframework.stereotype.Service;

import com.arthur.calculadorarpg.armadura.Armadura;
import com.arthur.calculadorarpg.armadura.ArmaduraRepository;
import com.arthur.calculadorarpg.inventario.Inventario;
import com.arthur.calculadorarpg.inventario.InventarioRepository;
import com.arthur.calculadorarpg.personagem.Personagem;
import com.arthur.calculadorarpg.status.Status;
import com.arthur.calculadorarpg.status.StatusRepository;

@Service
public class ArmaService {

    private final ArmaRepository armaRepository;
    private final StatusRepository statusRepository;
    private final InventarioRepository inventarioRepository;
    private final ArmaduraRepository armaduraRepository;

    public ArmaService(
            ArmaRepository armaRepository,
            StatusRepository statusRepository,
            InventarioRepository inventarioRepository,
            ArmaduraRepository armaduraRepository) {
        this.armaRepository = armaRepository;
        this.statusRepository = statusRepository;
        this.inventarioRepository = inventarioRepository;
        this.armaduraRepository = armaduraRepository;
    }

    public Arma criarArma(Arma arma) {
        return armaRepository.save(arma);
    }

    public List<Arma> listarArmas() {
        return armaRepository.findByInventarioIsNull();
    }

    public Arma atualizarArma(Long id, Arma dados) {
        Arma arma = armaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Arma não encontrada"));

        if (dados.getArmaNome() != null) {
            arma.setArmaNome(dados.getArmaNome());
        }

        if (dados.getArmaPreco() != null) {
            arma.setArmaPreco(dados.getArmaPreco());
        }

        return armaRepository.save(arma);
    }

    public void deletarArma(Long id) {
        Arma arma = armaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Arma não encontrada"));

        armaRepository.delete(arma);
    }

    public void venderArma(Long armaId) {
        Arma arma = armaRepository.findById(armaId)
                .orElseThrow(() -> new RuntimeException("Arma não encontrada"));

        Inventario inventario = arma.getInventario();
        if (inventario == null) {
            throw new RuntimeException("A arma não está em nenhum inventário");
        }

        Personagem personagem = inventario.getPersonagem();
        if (personagem == null) {
            throw new RuntimeException("Personagem não encontrado");
        }

        Status status = personagem.getStatus();
        if (status == null) {
            throw new RuntimeException("Status não encontrado");
        }

        Integer dinheiro = status.getStatusDinheiro() == null ? 0 : status.getStatusDinheiro();
        Integer preco = arma.getArmaPreco() == null ? 0 : arma.getArmaPreco();

        status.setStatusDinheiro(dinheiro + preco);

        statusRepository.save(status);
        armaRepository.delete(arma);

        recalcularEspacoAtual(personagem.getId());
    }

    public Arma criarArmaNoInventario(Long inventarioId, Arma arma) {
        Inventario inventario = inventarioRepository.findById(inventarioId)
                .orElseThrow(() -> new RuntimeException("Inventário não encontrado"));

        arma.setInventario(inventario);

        Arma armaSalva = armaRepository.save(arma);
        recalcularEspacoAtual(inventario.getPersonagem().getId());

        return armaSalva;
    }

    public Arma adicionarArmaExistenteNoInventario(Long armaId, Long inventarioId) {
        Arma armaOriginal = armaRepository.findById(armaId)
                .orElseThrow(() -> new RuntimeException("Arma não encontrada"));

        Inventario inventario = inventarioRepository.findById(inventarioId)
                .orElseThrow(() -> new RuntimeException("Inventário não encontrado"));

        Arma novaArma = copiarArmaParaInventario(armaOriginal, inventario);

        Arma armaSalva = armaRepository.save(novaArma);
        recalcularEspacoAtual(inventario.getPersonagem().getId());

        return armaSalva;
    }

    public void removerArmaDoInventario(Long armaId) {
    Arma arma = armaRepository.findById(armaId)
            .orElseThrow(() -> new RuntimeException("Arma não encontrada"));

    if (arma.getInventario() == null) {
        throw new RuntimeException("Essa arma não está em inventário");
    }

    Long personagemId = arma.getInventario().getPersonagem().getId();

    armaRepository.delete(arma);

    recalcularEspacoAtual(personagemId);
}

    public Arma buscarPorId(Long armaId) {
        return armaRepository.findById(armaId)
                .orElseThrow(() -> new RuntimeException("Arma não encontrada"));
    }

    private void recalcularEspacoAtual(Long personagemId) {
        Inventario inventarioBase = inventarioRepository.findFirstByPersonagemIdAndItemIsNull(personagemId)
                .orElseThrow(() -> new RuntimeException("Inventário base não encontrado"));

        Personagem personagem = inventarioBase.getPersonagem();
        if (personagem == null) {
            throw new RuntimeException("Personagem não encontrado");
        }

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
        statusRepository.save(status);
    }

    private Arma copiarArmaParaInventario(Arma armaOriginal, Inventario inventario) {
        Arma novaArma = new Arma();

        novaArma.setInventario(inventario);

        novaArma.setArmaNome(armaOriginal.getArmaNome());
        novaArma.setArmaTipoPericia(armaOriginal.getArmaTipoPericia());
        novaArma.setArmaTesteAtaque(armaOriginal.getArmaTesteAtaque());
        novaArma.setArmaBonusTesteAtaque(armaOriginal.getArmaBonusTesteAtaque());
        novaArma.setArmaPenalidadeTesteAtaque(armaOriginal.getArmaPenalidadeTesteAtaque());
        novaArma.setArmaQuantidadeDado(armaOriginal.getArmaQuantidadeDado());
        novaArma.setArmaTipoDado(armaOriginal.getArmaTipoDado());
        novaArma.setArmaBonusDano(armaOriginal.getArmaBonusDano());
        novaArma.setArmaPenalidadeDano(armaOriginal.getArmaPenalidadeDano());
        novaArma.setArmaCriticoMinimo(armaOriginal.getArmaCriticoMinimo());
        novaArma.setArmaCriticoMultiplicador(armaOriginal.getArmaCriticoMultiplicador());
        novaArma.setArmaAlcance(armaOriginal.getArmaAlcance());
        novaArma.setArmaTipo(armaOriginal.getArmaTipo());
        novaArma.setArmaEspaco(armaOriginal.getArmaEspaco());
        novaArma.setArmaPreco(armaOriginal.getArmaPreco());
        novaArma.setArmaBonusPv(armaOriginal.getArmaBonusPv());
        novaArma.setArmaPenalidadePv(armaOriginal.getArmaPenalidadePv());
        novaArma.setArmaBonusPm(armaOriginal.getArmaBonusPm());
        novaArma.setArmaPenalidadePm(armaOriginal.getArmaPenalidadePm());

        return novaArma;
    }
}