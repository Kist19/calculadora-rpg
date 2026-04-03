package com.arthur.calculadorarpg.status;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arthur.calculadorarpg.personagem.Personagem;
import com.arthur.calculadorarpg.personagem.PersonagemRepository;

@Service
public class StatusService {

    private final StatusRepository statusRepository;
    private final PersonagemRepository personagemRepository;

    public StatusService(StatusRepository statusRepository, PersonagemRepository personagemRepository) {
        this.statusRepository = statusRepository;
        this.personagemRepository = personagemRepository;
    }

    @Transactional
    public Status aplicarDano(Long personagemId, Integer dano) {

        if (dano == null) {
            throw new RuntimeException("O valor do dano não pode ser nulo");
        }

        if (dano < 0) {
            throw new RuntimeException("O valor do dano não pode ser negativo");
        }

        Personagem personagem = personagemRepository.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        Status status = personagem.getStatus();

        if (status == null) {
            throw new RuntimeException("Status do personagem não encontrado");
        }

        int vidaAtual = status.getStatusVidaAtual() != null ? status.getStatusVidaAtual() : 0;
        vidaAtual -= dano;

        if (vidaAtual < 0) {
            vidaAtual = 0;
        }

        status.setStatusVidaAtual(vidaAtual);

        return statusRepository.save(status);
    }

    @Transactional
    public Status gastarMana(Long personagemId, Integer mana) {

        if (mana == null) {
            throw new RuntimeException("O valor da mana não pode ser nulo");
        }

        if (mana < 0) {
            throw new RuntimeException("O valor da mana não pode ser negativo");
        }

        Personagem personagem = personagemRepository.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        Status status = personagem.getStatus();

        if (status == null) {
            throw new RuntimeException("Status do personagem não encontrado");
        }

        int manaAtual = status.getStatusManaAtual() != null ? status.getStatusManaAtual() : 0;
        manaAtual -= mana;

        if (manaAtual < 0) {
            manaAtual = 0;
        }

        status.setStatusManaAtual(manaAtual);

        return statusRepository.save(status);
    }

    @Transactional
    public Status criarStatus(Long personagemId, Status status) {
        Personagem personagem = personagemRepository.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado com id: " + personagemId));

        status.setPersonagem(personagem);
        personagem.setStatus(status);

        return statusRepository.save(status);
    }

    public List<Status> listarStatus() {
        return statusRepository.findAll();
    }

    public Status buscarStatusPorPersonagemId(Long personagemId) {
        Personagem personagem = personagemRepository.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        Status status = personagem.getStatus();

        if (status == null) {
            throw new RuntimeException("Status não encontrado para o personagem");
        }

        return status;
    }

    @Transactional
    public Status atualizarStatusPorPersonagem(Long personagemId, Status statusAtualizado) {
        Personagem personagem = personagemRepository.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        Status statusExistente = personagem.getStatus();

        if (statusExistente == null) {
            throw new RuntimeException("Status não encontrado para o personagem");
        }

        if (statusAtualizado.getStatusVidaBase() != null) {
            statusExistente.setStatusVidaBase(statusAtualizado.getStatusVidaBase());
        }

        if (statusAtualizado.getStatusVidaAtual() != null) {
            statusExistente.setStatusVidaAtual(statusAtualizado.getStatusVidaAtual());
        }

        if (statusAtualizado.getStatusManaBase() != null) {
            statusExistente.setStatusManaBase(statusAtualizado.getStatusManaBase());
        }

        if (statusAtualizado.getStatusManaAtual() != null) {
            statusExistente.setStatusManaAtual(statusAtualizado.getStatusManaAtual());
        }

        if (statusAtualizado.getStatusDefesa() != null) {
            statusExistente.setStatusDefesa(statusAtualizado.getStatusDefesa());
        }

        if (statusAtualizado.getStatusDinheiro() != null) {
            statusExistente.setStatusDinheiro(statusAtualizado.getStatusDinheiro());
        }

        if (statusAtualizado.getStatusEspacoBase() != null) {
            statusExistente.setStatusEspacoBase(statusAtualizado.getStatusEspacoBase());
        }

        return statusRepository.save(statusExistente);
    }

    @Transactional
    public void deletarStatusPorPersonagemId(Long personagemId) {
        Personagem personagem = personagemRepository.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        Status status = personagem.getStatus();

        if (status == null) {
            throw new RuntimeException("Status não encontrado para o personagem");
        }

        personagem.setStatus(null);
        personagemRepository.save(personagem);

        statusRepository.delete(status);
        statusRepository.flush();
    }
}