package com.arthur.calculadorarpg.armadura;

import java.util.List;

import org.springframework.stereotype.Service;

import com.arthur.calculadorarpg.arma.Arma;
import com.arthur.calculadorarpg.arma.ArmaRepository;
import com.arthur.calculadorarpg.inventario.Inventario;
import com.arthur.calculadorarpg.inventario.InventarioRepository;
import com.arthur.calculadorarpg.personagem.Personagem;
import com.arthur.calculadorarpg.status.Status;
import com.arthur.calculadorarpg.status.StatusRepository;

@Service
public class ArmaduraService {

    private final ArmaduraRepository armaduraRepository;
    private final StatusRepository statusRepository;
    private final InventarioRepository inventarioRepository;
    private final ArmaRepository armaRepository;

    public ArmaduraService(
            ArmaduraRepository armaduraRepository,
            StatusRepository statusRepository,
            InventarioRepository inventarioRepository,
            ArmaRepository armaRepository) {

        this.armaduraRepository = armaduraRepository;
        this.statusRepository = statusRepository;
        this.inventarioRepository = inventarioRepository;
        this.armaRepository = armaRepository;
    }

    public Armadura criarArmadura(Armadura armadura) {
        return armaduraRepository.save(armadura);
    }

    public List<Armadura> listarArmaduras() {
        return armaduraRepository.findByInventarioIsNull();
    }

    public Armadura atualizarArmadura(Long id, Armadura dadosAtualizados) {
        Armadura armadura = armaduraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Armadura não encontrada"));

        if (dadosAtualizados.getArmaduraNome() != null) {
            armadura.setArmaduraNome(dadosAtualizados.getArmaduraNome());
        }

        if (dadosAtualizados.getArmaduraPreco() != null) {
            armadura.setArmaduraPreco(dadosAtualizados.getArmaduraPreco());
        }

        if (dadosAtualizados.getArmaduraBonusDefesa() != null) {
            armadura.setArmaduraBonusDefesa(dadosAtualizados.getArmaduraBonusDefesa());
        }

        if (dadosAtualizados.getArmaduraPenalidadeArmadura() != null) {
            armadura.setArmaduraPenalidadeArmadura(dadosAtualizados.getArmaduraPenalidadeArmadura());
        }

        armadura.setArmaduraEspaco(dadosAtualizados.getArmaduraEspaco());

        if (dadosAtualizados.getArmaduraTipo() != null) {
            armadura.setArmaduraTipo(dadosAtualizados.getArmaduraTipo());
        }

        return armaduraRepository.save(armadura);
    }

    public void deletarArmadura(Long id) {
        Armadura armadura = armaduraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Armadura não encontrada"));

        armaduraRepository.delete(armadura);
    }

    public void venderArmadura(Long armaduraId) {
        Armadura armadura = armaduraRepository.findById(armaduraId)
                .orElseThrow(() -> new RuntimeException("Armadura não encontrada"));

        Inventario inventario = armadura.getInventario();
        if (inventario == null) {
            throw new RuntimeException("A armadura não está em nenhum inventário");
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
        Integer preco = armadura.getArmaduraPreco() == null ? 0 : armadura.getArmaduraPreco();

        status.setStatusDinheiro(dinheiro + preco);

        statusRepository.save(status);
        armaduraRepository.delete(armadura);

        recalcularEspacoAtual(personagem.getId());
    }

    public Armadura criarArmaduraNoInventario(Long inventarioId, Armadura armadura) {
        Inventario inventario = inventarioRepository.findById(inventarioId)
                .orElseThrow(() -> new RuntimeException("Inventário não encontrado"));

        armadura.setInventario(inventario);

        Armadura armaduraSalva = armaduraRepository.save(armadura);
        recalcularEspacoAtual(inventario.getPersonagem().getId());

        return armaduraSalva;
    }

    public Armadura adicionarArmaduraExistenteNoInventario(Long armaduraId, Long inventarioId) {
        Armadura armaduraOriginal = armaduraRepository.findById(armaduraId)
                .orElseThrow(() -> new RuntimeException("Armadura não encontrada"));

        Inventario inventario = inventarioRepository.findById(inventarioId)
                .orElseThrow(() -> new RuntimeException("Inventário não encontrado"));

        Armadura novaArmadura = copiarArmaduraParaInventario(armaduraOriginal, inventario);

        Armadura armaduraSalva = armaduraRepository.save(novaArmadura);
        recalcularEspacoAtual(inventario.getPersonagem().getId());

        return armaduraSalva;
    }

    private Armadura copiarArmaduraParaInventario(Armadura armaduraOriginal, Inventario inventario) {
        Armadura novaArmadura = new Armadura();

        novaArmadura.setInventario(inventario);

        novaArmadura.setArmaduraNome(armaduraOriginal.getArmaduraNome());
        novaArmadura.setArmaduraPreco(armaduraOriginal.getArmaduraPreco());
        novaArmadura.setArmaduraBonusDefesa(armaduraOriginal.getArmaduraBonusDefesa());
        novaArmadura.setArmaduraPenalidadeArmadura(armaduraOriginal.getArmaduraPenalidadeArmadura());
        novaArmadura.setArmaduraEspaco(armaduraOriginal.getArmaduraEspaco());
        novaArmadura.setArmaduraTipo(armaduraOriginal.getArmaduraTipo());

        return novaArmadura;
    }

    public void removerArmaduraDoInventario(Long armaduraId) {
        Armadura armadura = armaduraRepository.findById(armaduraId)
                .orElseThrow(() -> new RuntimeException("Armadura não encontrada"));

        if (armadura.getInventario() == null) {
            throw new RuntimeException("Essa armadura não está em inventário");
        }

        Long personagemId = armadura.getInventario().getPersonagem().getId();

        armaduraRepository.delete(armadura);

        recalcularEspacoAtual(personagemId);
    }

    public Armadura buscarPorId(Long armaduraId) {
        return armaduraRepository.findById(armaduraId)
                .orElseThrow(() -> new RuntimeException("Armadura não encontrada"));
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
}