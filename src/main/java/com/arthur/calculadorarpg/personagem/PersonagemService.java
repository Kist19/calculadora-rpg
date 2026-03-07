package com.arthur.calculadorarpg.personagem;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PersonagemService {
    private final PersonagemRepository personagemRepositorio;

    public PersonagemService(PersonagemRepository personagemRepositorio) {
        this.personagemRepositorio = personagemRepositorio;
    }

    public Personagem criarPersonagem(Personagem personagem) {
        return personagemRepositorio.save(personagem);
    }

    public List<Personagem> listarPersonagens() {
        return personagemRepositorio.findAll();
    }
}
