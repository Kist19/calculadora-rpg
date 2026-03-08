package com.arthur.calculadorarpg.armadura;

import org.springframework.stereotype.Service;

import com.arthur.calculadorarpg.inventario.Inventario;
import com.arthur.calculadorarpg.personagem.Personagem;
import com.arthur.calculadorarpg.status.Status;
import com.arthur.calculadorarpg.status.StatusRepository;

@Service
public class ArmaduraService {

    private final ArmaduraRepository armaduraRepositorio;
    private final StatusRepository statusRepositorio;

    public ArmaduraService(
            ArmaduraRepository armaduraRepositorio,
            StatusRepository statusRepositorio) {

        this.armaduraRepositorio = armaduraRepositorio;
        this.statusRepositorio = statusRepositorio;
    }

    public Armadura criarArmadura(Armadura armadura) {
        return armaduraRepositorio.save(armadura);
    }

    public java.util.List<Armadura> listarArmaduras() {
        return armaduraRepositorio.findAll();
    }

    public Armadura atualizarArmadura(Long id, Armadura dadosAtualizados) {

        Armadura armadura = armaduraRepositorio.findById(id)
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

        return armaduraRepositorio.save(armadura);
    }

    public void deletarArmadura(Long id) {

        Armadura armadura = armaduraRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Armadura não encontrada"));

        armaduraRepositorio.delete(armadura);
    }

    public void venderArmadura(Long armaduraId) {

        Armadura armadura = armaduraRepositorio.findById(armaduraId).orElseThrow();

        Inventario inventario = armadura.getInventario();

        Personagem personagem = inventario.getPersonagem();

        Status status = personagem.getStatus();

        Integer dinheiro = status.getStatusDinheiro() == null ? 0 : status.getStatusDinheiro();

        status.setStatusDinheiro(dinheiro + armadura.getArmaduraPreco());

        statusRepositorio.save(status);

        armaduraRepositorio.delete(armadura);
    }
}