package com.arthur.calculadorarpg.arma;

import org.springframework.stereotype.Service;

import com.arthur.calculadorarpg.inventario.Inventario;
import com.arthur.calculadorarpg.personagem.Personagem;
import com.arthur.calculadorarpg.status.Status;
import com.arthur.calculadorarpg.status.StatusRepository;

@Service
public class ArmaService {
    private final ArmaRepository armaRepositorio;
    private final StatusRepository statusRepositorio;

    public ArmaService(ArmaRepository armaRepository, StatusRepository statusRepository) {
        this.armaRepositorio = armaRepository;
        this.statusRepositorio = statusRepository;
    }

    public Arma criarArma(Arma arma) {
        return armaRepositorio.save(arma);
    }

    public java.util.List<Arma> listarArmas() {
        return armaRepositorio.findAll();
    }

    public Arma atualizarArma(Long id, Arma dados) {

        Arma arma = armaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Arma não encontrada"));

        if (dados.getArmaNome() != null) {
            arma.setArmaNome(dados.getArmaNome());
        }

        if (dados.getArmaPreco() != null) {
            arma.setArmaPreco(dados.getArmaPreco());
        }

        return armaRepositorio.save(arma);
    }

    public void deletarArma(Long id) {

        Arma arma = armaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Arma não encontrada"));

        armaRepositorio.delete(arma);
    }

    public void venderArma(Long armaId) {

        Arma arma = armaRepositorio.findById(armaId).orElseThrow();

        Inventario inventario = arma.getInventario();

        Personagem personagem = inventario.getPersonagem();

        Status status = personagem.getStatus();

        Integer dinheiro = status.getStatusDinheiro() == null ? 0 : status.getStatusDinheiro();

        status.setStatusDinheiro(dinheiro + arma.getArmaPreco());

        statusRepositorio.save(status);

        armaRepositorio.delete(arma);
    }

}
