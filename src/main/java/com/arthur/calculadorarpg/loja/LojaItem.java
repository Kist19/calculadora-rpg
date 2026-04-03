package com.arthur.calculadorarpg.loja;

import com.arthur.calculadorarpg.armadura.Armadura;
import com.arthur.calculadorarpg.arma.Arma;
import com.arthur.calculadorarpg.item.Item;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_loja_item")
public class LojaItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "loja_id", nullable = false)
    private Loja loja;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = true)
    private Item item;

    @ManyToOne
    @JoinColumn(name = "arma_id", nullable = true)
    private Arma arma;

    @ManyToOne
    @JoinColumn(name = "armadura_id", nullable = true)
    private Armadura armadura;

    @Column(nullable = false)
    private Integer lojaPreco;

    @Column(nullable = false)
    private Integer lojaQuantidade;

    @Column(nullable = false)
    private Boolean disponivel = true;

    public LojaItem() {
    }

    public Long getId() {
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

    public Arma getArma() {
        return arma;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }

    public Armadura getArmadura() {
        return armadura;
    }

    public void setArmadura(Armadura armadura) {
        this.armadura = armadura;
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

    @Transient
    public String getLojaCategoria() {
        if (item != null) {
            return "ITEM";
        }
        if (arma != null) {
            return "ARMA";
        }
        if (armadura != null) {
            return "ARMADURA";
        }
        return null;
    }
}