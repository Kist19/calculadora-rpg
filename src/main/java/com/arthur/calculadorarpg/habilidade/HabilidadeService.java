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

        statusRepository.save(status);

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

        return habilidadeRepository.save(habilidade);
    }

    public void deletarHabilidade(Long id) {

        Habilidade habilidade = habilidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habilidade não encontrada"));

        habilidadeRepository.delete(habilidade);
    }

}
