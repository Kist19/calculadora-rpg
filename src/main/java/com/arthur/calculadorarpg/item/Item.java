package com.arthur.calculadorarpg.item;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemNome;

    @Enumerated(EnumType.STRING)
    private ItemTipo itemTipo;

    private Integer itemPreco;

    private Integer bonusAtaque = 0;
    private Integer penalidadeAtaque = 0;

    private Integer bonusDano = 0;
    private Integer penalidadeDano = 0;

    private Integer bonusPv = 0;
    private Integer penalidadePv = 0;

    private Integer bonusPm = 0;
    private Integer penalidadePm = 0;

    public Item() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemNome() {
        return itemNome;
    }

    public void setItemNome(String itemNome) {
        this.itemNome = itemNome;
    }

    public ItemTipo getItemTipo() {
        return itemTipo;
    }

    public void setItemTipo(ItemTipo itemTipo) {
        this.itemTipo = itemTipo;
    }

    public Integer getItemPreco() {
        return itemPreco;
    }

    public void setItemPreco(Integer itemPreco) {
        this.itemPreco = itemPreco;
    }

    public Integer getBonusAtaque() {
        return bonusAtaque;
    }

    public void setBonusAtaque(Integer bonusAtaque) {
        this.bonusAtaque = bonusAtaque;
    }

    public Integer getPenalidadeAtaque() {
        return penalidadeAtaque;
    }

    public void setPenalidadeAtaque(Integer penalidadeAtaque) {
        this.penalidadeAtaque = penalidadeAtaque;
    }

    public Integer getBonusDano() {
        return bonusDano;
    }

    public void setBonusDano(Integer bonusDano) {
        this.bonusDano = bonusDano;
    }

    public Integer getPenalidadeDano() {
        return penalidadeDano;
    }

    public void setPenalidadeDano(Integer penalidadeDano) {
        this.penalidadeDano = penalidadeDano;
    }

    public Integer getBonusPv() {
        return bonusPv;
    }

    public void setBonusPv(Integer bonusPv) {
        this.bonusPv = bonusPv;
    }

    public Integer getPenalidadePv() {
        return penalidadePv;
    }

    public void setPenalidadePv(Integer penalidadePv) {
        this.penalidadePv = penalidadePv;
    }

    public Integer getBonusPm() {
        return bonusPm;
    }

    public void setBonusPm(Integer bonusPm) {
        this.bonusPm = bonusPm;
    }

    public Integer getPenalidadePm() {
        return penalidadePm;
    }

    public void setPenalidadePm(Integer penalidadePm) {
        this.penalidadePm = penalidadePm;
    }
    
}
