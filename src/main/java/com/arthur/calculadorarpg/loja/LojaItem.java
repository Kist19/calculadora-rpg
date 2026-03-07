package com.arthur.calculadorarpg.loja;

import com.arthur.calculadorarpg.item.Item;
import jakarta.persistence.*;

@Entity
@Table(name= "tb_loja_item")
public class LojaItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "loja_id", nullable = false)
    private Loja loja;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(nullable = false)
    private Integer lojaPreco;

    @Column(nullable = false)
    private Integer lojaQuantidade;

    @Column(nullable = false)
    private Boolean disponivel = true;

    public LojaItem(){

    }

    public Long getId(){
        return id;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getLojaPreco() {
        return lojaPreco;
    }

    public void setLojaPreco(Integer lojaPreco) {
        this.lojaPreco = lojaPreco;
    }

    public Integer getLojaQuantidade() {
        return lojaQuantidade;
    }

    public void setLojaQuantidade(Integer lojaQuantidade) {
        this.lojaQuantidade = lojaQuantidade;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }
    
}
