package com.arthur.calculadorarpg.habilidade;

import org.springframework.stereotype.Service;

import com.arthur.calculadorarpg.personagem.Personagem;
import com.arthur.calculadorarpg.personagem.PersonagemRepository;
import com.arthur.calculadorarpg.status.Status;
import com.arthur.calculadorarpg.status.StatusRepository;

@Service
public class HabilidadeService {

    private final HabilidadeRepository habilidadeRepository;
    private final PersonagemRepository personagemRepository;
    private final StatusRepository statusRepository;

    public HabilidadeService(
            HabilidadeRepository habilidadeRepository,
            PersonagemRepository personagemRepository,
            StatusRepository statusRepository) {
        this.habilidadeRepository = habilidadeRepository;
        this.personagemRepository = personagemRepository;
        this.statusRepository = statusRepository;
    }

    public String usarHabilidade(Long personagemId, Long habilidadeId) {

        Personagem personagem = personagemRepository.findById(personagemId).orElseThrow();
        Habilidade habilidade = habilidadeRepository.findById(habilidadeId).orElseThrow();

        Status status = personagem.getStatus();

        if (status == null) {
            return "O personagem não possui status cadastrado.";
        }

        Integer manaAtual = status.getStatusManaAtual() != null ? status.getStatusManaAtual() : 0;
        Integer custo = habilidade.getHabilidadeCustoPm() != null ? habilidade.getHabilidadeCustoPm() : 0;

        if (manaAtual < custo) {
            return "Mana insuficiente para usar a habilidade.";
        }

        status.setStatusManaAtual(manaAtual - custo);

        Integer duracao = habilidade.getHabilidadeDuracaoTurno() != null
                ? habilidade.getHabilidadeDuracaoTurno()
                : 0;

        personagem.getHabilidadesTurnosRestantesNaCena()
                .put(habilidade.getHabilidadeNome(), duracao);

        statusRepository.save(status);
        personagemRepository.save(personagem);

        return "Habilidade usada com sucesso.";
    }

    public Habilidade atualizarHabilidade(Long id, Habilidade dadosAtualizados) {

        Habilidade habilidade = habilidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habilidade não encontrada"));

        if (dadosAtualizados.getHabilidadeNome() != null) {
            habilidade.setHabilidadeNome(dadosAtualizados.getHabilidadeNome());
        }

        if (dadosAtualizados.getHabilidadeCustoPm() != null) {
            habilidade.setHabilidadeCustoPm(dadosAtualizados.getHabilidadeCustoPm());
        }

        if (dadosAtualizados.getHabilidadeBonusAtaque() != null) {
            habilidade.setHabilidadeBonusAtaque(dadosAtualizados.getHabilidadeBonusAtaque());
        }

        if (dadosAtualizados.getHabilidadeBonusDano() != null) {
            habilidade.setHabilidadeBonusDano(dadosAtualizados.getHabilidadeBonusDano());
        }

        if (dadosAtualizados.getHabilidadeBonusPv() != null) {
            habilidade.setHabilidadeBonusPv(dadosAtualizados.getHabilidadeBonusPv());
        }

        if (dadosAtualizados.getHabilidadeBonusPm() != null) {
            habilidade.setHabilidadeBonusPm(dadosAtualizados.getHabilidadeBonusPm());
        }

        if (dadosAtualizados.getHabilidadePenalidadeAtaque() != null) {
            habilidade.setHabilidadePenalidadeAtaque(dadosAtualizados.getHabilidadePenalidadeAtaque());
        }

        if (dadosAtualizados.getHabilidadePenalidadeDano() != null) {
            habilidade.setHabilidadePenalidadeDano(dadosAtualizados.getHabilidadePenalidadeDano());
        }

        if (dadosAtualizados.getHabilidadeDuracaoTurno() != null) {
            habilidade.setHabilidadeDuracaoTurno(dadosAtualizados.getHabilidadeDuracaoTurno());
        }

        if (dadosAtualizados.getHabilidadeQuantidadeDado() != null) {
            habilidade.setHabilidadeQuantidadeDado(dadosAtualizados.getHabilidadeQuantidadeDado());
        }

        if (dadosAtualizados.getHabilidadeTipoDado() != null) {
            habilidade.setHabilidadeTipoDado(dadosAtualizados.getHabilidadeTipoDado());
        }

        if (dadosAtualizados.getHabilidadeDadoTipoAcao() != null) {
            habilidade.setHabilidadeDadoTipoAcao(dadosAtualizados.getHabilidadeDadoTipoAcao());
        }

        return habilidadeRepository.save(habilidade);
    }

    public void deletarHabilidade(Long id) {

        Habilidade habilidade = habilidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habilidade não encontrada"));

        habilidadeRepository.delete(habilidade);
    }

    public Personagem vincularHabilidadeAoPersonagem(Long personagemId, Long habilidadeId) {
        Personagem personagem = personagemRepository.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        Habilidade habilidade = habilidadeRepository.findById(habilidadeId)
                .orElseThrow(() -> new RuntimeException("Habilidade não encontrada"));

        boolean jaExiste = personagem.getHabilidades().stream()
                .anyMatch(h -> h.getId().equals(habilidadeId));

        if (!jaExiste) {
            personagem.getHabilidades().add(habilidade);
        }

        return personagemRepository.save(personagem);
    }

    public Habilidade buscarPorId(Long habilidadeId) {
        return habilidadeRepository.findById(habilidadeId)
                .orElseThrow(() -> new RuntimeException("Habilidade não encontrada"));
    }

    public void desvincularHabilidadeDoPersonagem(Long habilidadeId, Long personagemId) {

        Personagem personagem = personagemRepository.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        boolean removida = personagem.getHabilidades()
                .removeIf(h -> h.getId().equals(habilidadeId));

        if (!removida) {
            throw new RuntimeException("Habilidade não está vinculada ao personagem");
        }

        personagemRepository.save(personagem);
    }
}