package com.arthur.calculadorarpg.item;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.arthur.calculadorarpg.inventario.Inventario;
import com.arthur.calculadorarpg.inventario.InventarioRepository;
import com.arthur.calculadorarpg.status.Status;
import com.arthur.calculadorarpg.status.StatusRepository;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final InventarioRepository inventarioRepository;
    private final StatusRepository statusRepository;

    public ItemService(
            ItemRepository itemRepository,
            InventarioRepository inventarioRepository,
            StatusRepository statusRepository) {
        this.itemRepository = itemRepository;
        this.inventarioRepository = inventarioRepository;
        this.statusRepository = statusRepository;
    }

    public Item criarItem(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> listarItens() {
        return itemRepository.findAll();
    }

    public String venderItem(Long personagemId, Long itemId) {
        Optional<Inventario> inventarioOpt = inventarioRepository.findByPersonagemIdAndItemId(personagemId, itemId);

        if (inventarioOpt.isEmpty()) {
            return "Item não encontrado no inventário do personagem.";
        }

        Inventario inventario = inventarioOpt.get();

        if (inventario.getPersonagem() == null) {
            return "Personagem não encontrado.";
        }

        if (inventario.getItem() == null) {
            return "Item inválido.";
        }

        Status status = inventario.getPersonagem().getStatus();
        if (status == null) {
            return "O personagem não possui status cadastrado.";
        }

        Integer dinheiroAtual = status.getDinheiro() != null ? status.getDinheiro() : 0;
        Integer precoItem = inventario.getItem().getItemPreco() != null ? inventario.getItem().getItemPreco() : 0;

        status.setDinheiro(dinheiroAtual + precoItem);
        statusRepository.save(status);

        Integer quantidadeAtual = inventario.getInventarioQuantidade() != null
                ? inventario.getInventarioQuantidade()
                : 0;

        if (quantidadeAtual > 1) {
            inventario.setInventarioQuantidade(quantidadeAtual - 1);
            inventarioRepository.save(inventario);
        } else {
            inventarioRepository.delete(inventario);
        }

        return "Item vendido com sucesso.";
    }
}
