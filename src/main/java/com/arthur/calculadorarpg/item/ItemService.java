package com.arthur.calculadorarpg.item;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.arthur.calculadorarpg.inventario.Inventario;
import com.arthur.calculadorarpg.inventario.InventarioRepository;
import com.arthur.calculadorarpg.loja.LojaItemRepository;
import com.arthur.calculadorarpg.status.Status;
import com.arthur.calculadorarpg.status.StatusRepository;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final InventarioRepository inventarioRepository;
    private final StatusRepository statusRepository;
    private final LojaItemRepository lojaItemRepository;

    public ItemService(
            ItemRepository itemRepository,
            InventarioRepository inventarioRepository,
            StatusRepository statusRepository, LojaItemRepository lojaItemRepository) {
        this.itemRepository = itemRepository;
        this.inventarioRepository = inventarioRepository;
        this.statusRepository = statusRepository;
        this.lojaItemRepository = lojaItemRepository;
    }

    public Item criarItem(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> listarItens() {
        return itemRepository.findAll();
    }

    public Item atualizarItem(Long id, Item dadosAtualizados) {

        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        if (dadosAtualizados.getItemNome() != null) {
            item.setItemNome(dadosAtualizados.getItemNome());
        }

        if (dadosAtualizados.getItemTipo() != null) {
            item.setItemTipo(dadosAtualizados.getItemTipo());
        }

        if (dadosAtualizados.getItemPreco() != null) {
            item.setItemPreco(dadosAtualizados.getItemPreco());
        }

        if (dadosAtualizados.getItemDuracaoTurno() != null) {
            item.setItemDuracaoTurno(dadosAtualizados.getItemDuracaoTurno());
        }

        if (dadosAtualizados.getItemEspaco() != null) {
            item.setItemEspaco(dadosAtualizados.getItemEspaco());
        }

        if (dadosAtualizados.getItemBonusAtaque() != null) {
            item.setItemBonusAtaque(dadosAtualizados.getItemBonusAtaque());
        }

        if (dadosAtualizados.getItemPenalidadeAtaque() != null) {
            item.setItemPenalidadeAtaque(dadosAtualizados.getItemPenalidadeAtaque());
        }

        if (dadosAtualizados.getItemBonusDano() != null) {
            item.setItemBonusDano(dadosAtualizados.getItemBonusDano());
        }

        if (dadosAtualizados.getItemPenalidadeDano() != null) {
            item.setItemPenalidadeDano(dadosAtualizados.getItemPenalidadeDano());
        }

        if (dadosAtualizados.getItemBonusPv() != null) {
            item.setItemBonusPv(dadosAtualizados.getItemBonusPv());
        }

        if (dadosAtualizados.getItemPenalidadePv() != null) {
            item.setItemPenalidadePv(dadosAtualizados.getItemPenalidadePv());
        }

        if (dadosAtualizados.getItemBonusPm() != null) {
            item.setItemBonusPm(dadosAtualizados.getItemBonusPm());
        }

        if (dadosAtualizados.getItemPenalidadePm() != null) {
            item.setItemPenalidadePm(dadosAtualizados.getItemPenalidadePm());
        }

        return itemRepository.save(item);
    }

    public void deletarItem(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        if (lojaItemRepository.existsByItemId(id)) {
            throw new RuntimeException("Não é possível deletar o item. Remova ele da loja primeiro.");
        }

        if (inventarioRepository.existsByItemId(id)) {
            throw new RuntimeException("Não é possível deletar o item. Remova ele do inventário primeiro.");
        }

        itemRepository.delete(item);
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

        Integer dinheiroAtual = status.getStatusDinheiro() != null ? status.getStatusDinheiro() : 0;
        Integer precoItem = inventario.getItem().getItemPreco() != null ? inventario.getItem().getItemPreco() : 0;

        status.setStatusDinheiro(dinheiroAtual + precoItem);
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

    public Item buscarPorId(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));
    }
}
