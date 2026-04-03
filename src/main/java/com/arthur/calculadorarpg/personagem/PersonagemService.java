package com.arthur.calculadorarpg.personagem;

import java.util.List;

import org.springframework.stereotype.Service;

import com.arthur.calculadorarpg.armadura.Armadura;
import com.arthur.calculadorarpg.armadura.ArmaduraRepository;
import com.arthur.calculadorarpg.armadura.ArmaduraTipo;
import com.arthur.calculadorarpg.arma.Arma;
import com.arthur.calculadorarpg.arma.ArmaRepository;

@Service
public class PersonagemService {

    private final PersonagemRepository personagemRepositorio;
    private final ArmaRepository armaRepositorio;
    private final ArmaduraRepository armaduraRepositorio;

    public PersonagemService(
            PersonagemRepository personagemRepositorio,
            ArmaRepository armaRepositorio,
            ArmaduraRepository armaduraRepositorio) {
        this.personagemRepositorio = personagemRepositorio;
        this.armaRepositorio = armaRepositorio;
        this.armaduraRepositorio = armaduraRepositorio;
    }

    public Personagem criarPersonagem(Personagem personagem) {
        return personagemRepositorio.save(personagem);
    }

    public List<Personagem> listarPersonagens() {
        return personagemRepositorio.findAll();
    }

    public Personagem atualizarPersonagem(Long id, Personagem dadosAtualizados) {

        Personagem personagem = personagemRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        if (dadosAtualizados.getPersonagemNome() != null) {
            personagem.setPersonagemNome(dadosAtualizados.getPersonagemNome());
        }

        if (dadosAtualizados.getPersonagemRaca() != null) {
            personagem.setPersonagemRaca(dadosAtualizados.getPersonagemRaca());
        }

        if (dadosAtualizados.getPersonagemClasse() != null) {
            personagem.setPersonagemClasse(dadosAtualizados.getPersonagemClasse());
        }

        if (dadosAtualizados.getPersonagemOrigem() != null) {
            personagem.setPersonagemOrigem(dadosAtualizados.getPersonagemOrigem());
        }

        if (dadosAtualizados.getPersonagemNivel() != 0) {
            personagem.setPersonagemNivel(dadosAtualizados.getPersonagemNivel());
        }

        return personagemRepositorio.save(personagem);
    }

    public void deletarPersonagem(Long id) {

        Personagem personagem = personagemRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        personagemRepositorio.delete(personagem);
    }

    public Personagem equiparArma(Long personagemId, Long armaId) {
        Personagem personagem = personagemRepositorio.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        Arma arma = armaRepositorio.findById(armaId)
                .orElseThrow(() -> new RuntimeException("Arma não encontrada"));

        if (arma.getInventario() == null
                || arma.getInventario().getPersonagem() == null
                || !arma.getInventario().getPersonagem().getId().equals(personagemId)) {
            throw new RuntimeException("Essa arma não pertence ao inventário do personagem");
        }

        personagem.setArmaEquipada(arma);
        return personagemRepositorio.save(personagem);
    }

    public Personagem desequiparArma(Long personagemId) {
        Personagem personagem = personagemRepositorio.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        personagem.setArmaEquipada(null);
        return personagemRepositorio.save(personagem);
    }

    public Personagem equiparArmadura(Long personagemId, Long armaduraId) {
        Personagem personagem = personagemRepositorio.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        Armadura armadura = armaduraRepositorio.findById(armaduraId)
                .orElseThrow(() -> new RuntimeException("Armadura não encontrada"));

        if (armadura.getInventario() == null
                || armadura.getInventario().getPersonagem() == null
                || !armadura.getInventario().getPersonagem().getId().equals(personagemId)) {
            throw new RuntimeException("Essa armadura não pertence ao inventário do personagem");
        }

        if (armadura.getArmaduraTipo() != ArmaduraTipo.ARMADURA) {
            throw new RuntimeException("O equipamento informado não é uma armadura");
        }

        personagem.setArmaduraEquipada(armadura);
        return personagemRepositorio.save(personagem);
    }

    public Personagem desequiparArmadura(Long personagemId) {
        Personagem personagem = personagemRepositorio.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        personagem.setArmaduraEquipada(null);
        return personagemRepositorio.save(personagem);
    }

    public Personagem equiparEscudo(Long personagemId, Long armaduraId) {
        Personagem personagem = personagemRepositorio.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        Armadura escudo = armaduraRepositorio.findById(armaduraId)
                .orElseThrow(() -> new RuntimeException("Escudo não encontrado"));

        if (!ArmaduraTipo.ESCUDO.equals(escudo.getArmaduraTipo())) {
            throw new RuntimeException("O equipamento informado não é um escudo");
        }

        personagem.setEscudoEquipado(escudo);
        return personagemRepositorio.save(personagem);
    }

    public Personagem desequiparEscudo(Long personagemId) {
        Personagem personagem = personagemRepositorio.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        personagem.setEscudoEquipado(null);
        return personagemRepositorio.save(personagem);
    }

    public Personagem ativarModoCombate(Long personagemId) {
        Personagem personagem = personagemRepositorio.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        personagem.setPersonagemEmCombate(true);
        return personagemRepositorio.save(personagem);
    }

    public Personagem desativarModoCombate(Long personagemId) {
        Personagem personagem = personagemRepositorio.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        personagem.setPersonagemEmCombate(false);
        return personagemRepositorio.save(personagem);
    }

    public Personagem alternarModoCombate(Long personagemId) {
        Personagem personagem = personagemRepositorio.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        personagem.setPersonagemEmCombate(!Boolean.TRUE.equals(personagem.getPersonagemEmCombate()));
        return personagemRepositorio.save(personagem);
    }

    public DadosCenaResponse buscarDadosCena(Long personagemId) {
        Personagem personagem = personagemRepositorio.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        DadosCenaResponse response = new DadosCenaResponse();
        response.setPersonagemNome(personagem.getPersonagemNome());
        response.setPersonagemEmCombate(personagem.getPersonagemEmCombate());
        response.setItensTurnosRestantesNaCena(personagem.getItensTurnosRestantesNaCena());
        response.setHabilidadesTurnosRestantesNaCena(personagem.getHabilidadesTurnosRestantesNaCena());

        return response;
    }
}