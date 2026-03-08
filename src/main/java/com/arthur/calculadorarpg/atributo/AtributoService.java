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

    public Atributo criarAtributo(Atributo atributo) {
        return atributoRepository.save(atributo);
    }

    public java.util.List<Atributo> listarAtributos() {
        return atributoRepository.findAll();
    }

    public Atributo atualizarAtributo(Long id, Atributo dadosAtualizados) {

        Atributo atributo = atributoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atributo não encontrado"));

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

    public void deletarAtributo(Long id) {

        Atributo atributo = atributoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atributo não encontrado"));

        Personagem personagem = atributo.getPersonagem();

        if (personagem != null) {
            personagem.setAtributo(null);
            personagemRepository.save(personagem);
        }

        atributoRepository.delete(atributo);
    }
}
