package com.arthur.calculadorarpg.atributo;

import org.springframework.stereotype.Service;

import com.arthur.calculadorarpg.personagem.Personagem;
import com.arthur.calculadorarpg.personagem.PersonagemRepository;

@Service
public class AtributoService {
    private final AtributoRepository atributoRepository;
    private final PersonagemRepository personagemRepository;

    public AtributoService(AtributoRepository atributoRepository, PersonagemRepository personagemRepository) {
        this.atributoRepository = atributoRepository;
        this.personagemRepository = personagemRepository;
    }

    public Atributo criarAtributo(Long personagemId, Atributo atributo) {

        Personagem personagem = personagemRepository.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        atributo.setPersonagem(personagem);

        return atributoRepository.save(atributo);
    }

    public java.util.List<Atributo> listarAtributos() {
        return atributoRepository.findAll();
    }

    public Atributo buscarAtributoPorPersonagemId(Long personagemId) {
        Personagem personagem = personagemRepository.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        Atributo atributo = personagem.getAtributo();

        if (atributo == null) {
            throw new RuntimeException("Atributo não encontrado para o personagem");
        }

        return atributo;
    }

    public Atributo atualizarAtributoPorPersonagemId(Long personagemId, Atributo dadosAtualizados) {
        Personagem personagem = personagemRepository.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        Atributo atributo = personagem.getAtributo();

        if (atributo == null) {
            throw new RuntimeException("Atributo não encontrado para o personagem");
        }

        if (dadosAtualizados.getAtributoForca() != null) {
            atributo.setAtributoForca(dadosAtualizados.getAtributoForca());
        }

        if (dadosAtualizados.getAtributoDestreza() != null) {
            atributo.setAtributoDestreza(dadosAtualizados.getAtributoDestreza());
        }

        if (dadosAtualizados.getAtributoConstituicao() != null) {
            atributo.setAtributoConstituicao(dadosAtualizados.getAtributoConstituicao());
        }

        if (dadosAtualizados.getAtributoInteligencia() != null) {
            atributo.setAtributoInteligencia(dadosAtualizados.getAtributoInteligencia());
        }

        if (dadosAtualizados.getAtributoSabedoria() != null) {
            atributo.setAtributoSabedoria(dadosAtualizados.getAtributoSabedoria());
        }

        if (dadosAtualizados.getAtributoCarisma() != null) {
            atributo.setAtributoCarisma(dadosAtualizados.getAtributoCarisma());
        }

        return atributoRepository.save(atributo);
    }

    public void deletarAtributoPorPersonagemId(Long personagemId) {
        Personagem personagem = personagemRepository.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        Atributo atributo = personagem.getAtributo();

        if (atributo == null) {
            throw new RuntimeException("Atributo não encontrado para o personagem");
        }

        personagem.setAtributo(null);
        personagemRepository.save(personagem);

        atributoRepository.delete(atributo);
    }
}
