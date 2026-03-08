package com.arthur.calculadorarpg.status;

import org.springframework.stereotype.Service;
import java.util.List;

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

    public Status criarStatus(Status status) {

        Long personagemId = status.getPersonagem().getId();

        Personagem personagem = personagemRepository.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        status.setPersonagem(personagem);

        return statusRepository.save(status);
    }

    public List<Status> listarStatus() {
        return statusRepository.findAll();
    }

    public Status atualizarStatus(Long id, Status dadosAtualizados) {

        Status status = statusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Status não encontrado"));

        if (dadosAtualizados.getStatusVidaBase() != null) {
            status.setStatusVidaBase(dadosAtualizados.getStatusVidaBase());
        }

        if (dadosAtualizados.getStatusVidaAtual() != null) {
            status.setStatusVidaAtual(dadosAtualizados.getStatusVidaAtual());
        }

        if (dadosAtualizados.getStatusManaBase() != null) {
            status.setStatusManaBase(dadosAtualizados.getStatusManaBase());
        }

        if (dadosAtualizados.getStatusManaAtual() != null) {
            status.setStatusManaAtual(dadosAtualizados.getStatusManaAtual());
        }

        if (dadosAtualizados.getStatusDefesa() != null) {
            status.setStatusDefesa(dadosAtualizados.getStatusDefesa());
        }

        if (dadosAtualizados.getStatusDinheiro() != null) {
            status.setStatusDinheiro(dadosAtualizados.getStatusDinheiro());
        }

        if (dadosAtualizados.getStatusEspacoBase() != null) {
            status.setStatusEspacoBase(dadosAtualizados.getStatusEspacoBase());
        }

        if (dadosAtualizados.getStatusEspacoAtual() != null) {
            status.setStatusEspacoAtual(dadosAtualizados.getStatusEspacoAtual());
        }

        return statusRepository.save(status);
    }

    public void deletarStatus(Long id) {

        Status status = statusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Status não encontrado"));

        Personagem personagem = status.getPersonagem();

        if (personagem != null) {
            personagem.setStatus(null);
            personagemRepository.save(personagem);
        }

        statusRepository.delete(status);
        statusRepository.flush();
    }
}
