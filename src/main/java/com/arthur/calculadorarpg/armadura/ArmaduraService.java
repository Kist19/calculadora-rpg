package com.arthur.calculadorarpg.armadura;

import org.springframework.stereotype.Service;

import com.arthur.calculadorarpg.inventario.Inventario;
import com.arthur.calculadorarpg.personagem.Personagem;
import com.arthur.calculadorarpg.status.Status;
import com.arthur.calculadorarpg.status.StatusRepository;
import com.arthur.calculadorarpg.personagem.PersonagemRepository;

@Service
public class ArmaduraService {
    private final ArmaduraRepository armaduraRepositorio;
    private final StatusRepository statusRepositorio;
    private final PersonagemRepository personagemRepositorio;

    public ArmaduraService(ArmaduraRepository armaduraRepositorio, StatusRepository statusRepositorio,
            PersonagemRepository personagemRepositorio) {
        this.armaduraRepositorio = armaduraRepositorio;
        this.statusRepositorio = statusRepositorio;
        this.personagemRepositorio = personagemRepositorio;
    }

    public Armadura criarArmadura(Armadura armadura) {
        return armaduraRepositorio.save(armadura);
    }

    public java.util.List<Armadura> listarArmaduras() {
        return armaduraRepositorio.findAll();
    }

    public void venderArmadura(Long armaduraId) {

        Armadura armadura = armaduraRepositorio.findById(armaduraId).orElseThrow();

        Inventario inventario = armadura.getInventario();

        Personagem personagem = inventario.getPersonagem();

        Status status = personagem.getStatus();

        Integer dinheiro = status.getDinheiro() == null ? 0 : status.getDinheiro();

        status.setDinheiro(dinheiro + armadura.getArmaduraPreco());

        statusRepositorio.save(status);

        armaduraRepositorio.delete(armadura);
    }

    public void equiparArmadura(Long personagemId, Long armaduraId) {

        Personagem personagem = personagemRepositorio.findById(personagemId).orElseThrow();

        Armadura armadura = armaduraRepositorio.findById(armaduraId).orElseThrow();

        if (!armadura.getInventario().getPersonagem().getId().equals(personagemId)) {
            throw new RuntimeException("Esta armadura não pertence ao personagem");
        }

        if (!ArmaduraTipo.ARMADURA.equals(armadura.getArmaduraTipo())) {
            throw new RuntimeException("Este item não é uma armadura");
        }

        personagem.setArmaduraEquipada(armadura);

        personagemRepositorio.save(personagem);
    }

    public void equiparEscudo(Long personagemId, Long armaduraId) {

        Personagem personagem = personagemRepositorio.findById(personagemId).orElseThrow();

        Armadura escudo = armaduraRepositorio.findById(armaduraId).orElseThrow();

        if (!escudo.getInventario().getPersonagem().getId().equals(personagemId)) {
            throw new RuntimeException("Este escudo não pertence ao personagem");
        }

        if (!ArmaduraTipo.ESCUDO.equals(escudo.getArmaduraTipo())) {
            throw new RuntimeException("Este item não é um escudo");
        }

        personagem.setEscudoEquipado(escudo);

        personagemRepositorio.save(personagem);
    }

    public Personagem desequiparArmadura(Long personagemId) {

        Personagem personagem = personagemRepositorio.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        personagem.setArmaduraEquipada(null);

        return personagemRepositorio.save(personagem);
    }

    public Personagem desequiparEscudo(Long personagemId) {

        Personagem personagem = personagemRepositorio.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        personagem.setEscudoEquipado(null);

        return personagemRepositorio.save(personagem);
    }

}
