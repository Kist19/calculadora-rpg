package com.arthur.calculadorarpg.loja;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.arthur.calculadorarpg.inventario.Inventario;
import com.arthur.calculadorarpg.inventario.InventarioRepository;
import com.arthur.calculadorarpg.personagem.Personagem;
import com.arthur.calculadorarpg.personagem.PersonagemRepository;
import com.arthur.calculadorarpg.status.Status;
import com.arthur.calculadorarpg.status.StatusRepository;

@Service
public class LojaService {
    private final LojaRepository lojaRepository;
    private final LojaItemRepository lojaItemRepository;
    private final PersonagemRepository personagemRepository;
    private final InventarioRepository inventarioRepository;
    private final StatusRepository statusRepository;

    public LojaService(LojaRepository lojaRepository,
            LojaItemRepository lojaItemRepository,
            PersonagemRepository personagemRepository,
            InventarioRepository inventarioRepository,
            StatusRepository statusRepository) {
        this.lojaRepository = lojaRepository;
        this.lojaItemRepository = lojaItemRepository;
        this.personagemRepository = personagemRepository;
        this.inventarioRepository = inventarioRepository;
        this.statusRepository = statusRepository;
    }

    public String comprarItem(Long personagemId, Long lojaItemId) {
        Optional<Personagem> personagemOpt = personagemRepository.findById(personagemId);
        Optional<LojaItem> lojaItemOpt = lojaItemRepository.findById(lojaItemId);

        if (personagemOpt.isEmpty()) {
            return "Personagem não encontrado.";
        }

        if (lojaItemOpt.isEmpty()) {
            return "Item da loja não encontrado.";
        }

        Personagem personagem = personagemOpt.get();
        LojaItem lojaItem = lojaItemOpt.get();

        if (Boolean.FALSE.equals(lojaItem.getDisponivel())) {
            return "Este item não está disponível para compra.";
        }

        if (lojaItem.getLojaQuantidade() == null || lojaItem.getLojaQuantidade() <= 0) {
            return "Item sem estoque na loja.";
        }

        Status status = personagem.getStatus();
        if (status == null) {
            return "O personagem não possui status cadastrado.";
        }

        Integer dinheiroAtual = status.getStatusDinheiro() != null ? status.getStatusDinheiro() : 0;
        Integer precoItem = lojaItem.getLojaPreco() != null ? lojaItem.getLojaPreco() : 0;

        if (dinheiroAtual < precoItem) {
            return "Dinheiro insuficiente.";
        }

        status.setStatusDinheiro(dinheiroAtual - precoItem);
        statusRepository.save(status);

        lojaItem.setLojaQuantidade(lojaItem.getLojaQuantidade() - 1);
        if (lojaItem.getLojaQuantidade() <= 0) {
            lojaItem.setDisponivel(false);
        }
        lojaItemRepository.save(lojaItem);

        Inventario inventarioExistente = inventarioRepository
                .findByPersonagemIdAndItemId(personagemId, lojaItem.getItem().getId())
                .orElse(null);

        if (inventarioExistente != null) {
            Integer quantidadeAtual = inventarioExistente.getInventarioQuantidade() != null
                    ? inventarioExistente.getInventarioQuantidade()
                    : 0;

            inventarioExistente.setInventarioQuantidade(quantidadeAtual + 1);
            inventarioRepository.save(inventarioExistente);
        } else {
            Inventario novoInventario = new Inventario();
            novoInventario.setPersonagem(personagem);
            novoInventario.setItem(lojaItem.getItem());
            novoInventario.setInventarioQuantidade(1);
            inventarioRepository.save(novoInventario);
        }

        return "Compra realizada com sucesso.";
    }

    public Loja atualizarLoja(Long id, Loja dadosAtualizados) {

        Loja loja = lojaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loja não encontrada"));

        if (dadosAtualizados.getLojaNome() != null) {
            loja.setLojaNome(dadosAtualizados.getLojaNome());
        }

        if (dadosAtualizados.getLojaTipo() != null) {
            loja.setLojaTipo(dadosAtualizados.getLojaTipo());
        }

        return lojaRepository.save(loja);
    }

    public void deletarLoja(Long id) {

        Loja loja = lojaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loja não encontrada"));

        lojaRepository.delete(loja);
    }

    public LojaItem atualizarLojaItem(Long id, LojaItem dadosAtualizados) {

        LojaItem lojaItem = lojaItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item da loja não encontrado"));

        if (dadosAtualizados.getLojaPreco() != null) {
            lojaItem.setLojaPreco(dadosAtualizados.getLojaPreco());
        }

        return lojaItemRepository.save(lojaItem);
    }

    public void deletarLojaItem(Long id) {

        LojaItem lojaItem = lojaItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item da loja não encontrado"));

        lojaItemRepository.delete(lojaItem);
    }
}