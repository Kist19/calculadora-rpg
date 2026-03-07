package com.arthur.calculadorarpg.arma;

import org.springframework.stereotype.Service;

import com.arthur.calculadorarpg.inventario.Inventario;
import com.arthur.calculadorarpg.personagem.Personagem;
import com.arthur.calculadorarpg.status.Status;
import com.arthur.calculadorarpg.status.StatusRepository;
import com.arthur.calculadorarpg.personagem.PersonagemRepository;

@Service
public class ArmaService {
    private final ArmaRepository armaRepositorio;
    private final StatusRepository statusRepositorio;
    private final PersonagemRepository personagemRepositorio;

    public ArmaService(ArmaRepository armaRepositorio, StatusRepository statusRepositorio,
            PersonagemRepository personagemRepositorio) {
        this.armaRepositorio = armaRepositorio;
        this.statusRepositorio = statusRepositorio;
        this.personagemRepositorio = personagemRepositorio;
    }

    public Arma criarArma(Arma arma) {
        return armaRepositorio.save(arma);
    }

    public java.util.List<Arma> listarArmas() {
        return armaRepositorio.findAll();
    }

    public void venderArma(Long armaId) {

        Arma arma = armaRepositorio.findById(armaId).orElseThrow();

        Inventario inventario = arma.getInventario();

        Personagem personagem = inventario.getPersonagem();

        Status status = personagem.getStatus();

        Integer dinheiro = status.getDinheiro() == null ? 0 : status.getDinheiro();

        status.setDinheiro(dinheiro + arma.getArmaPreco());

        statusRepositorio.save(status);

        armaRepositorio.delete(arma);
    }

    public void equiparArma(Long personagemId, Long armaId) {

        Personagem personagem = personagemRepositorio.findById(personagemId).orElseThrow();

        Arma arma = armaRepositorio.findById(armaId).orElseThrow();

        if (!arma.getInventario().getPersonagem().getId().equals(personagemId)) {
            throw new RuntimeException("Esta arma não pertence ao personagem");
        }

        personagem.setArmaEquipada(arma);

        personagemRepositorio.save(personagem);
    }
    public Personagem desequiparArma(Long personagemId) {

    Personagem personagem = personagemRepositorio.findById(personagemId)
            .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

    personagem.setArmaEquipada(null);

    return personagemRepositorio.save(personagem);
}

}
