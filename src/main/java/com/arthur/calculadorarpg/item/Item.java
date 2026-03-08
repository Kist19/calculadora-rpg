package com.arthur.calculadorarpg.item;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String itemNome;

    @Column(length = 25, nullable = false)
    @Enumerated(EnumType.STRING)
    private ItemTipo itemTipo;

    @Column(nullable = false)
    private Integer itemPreco;

    @Column(nullable = false)
    private Integer itemBonusAtaque = 0;
    @Column(nullable = false)
    private Integer itemPenalidadeAtaque = 0;

    @Column(nullable = false)
    private Integer itemBonusDano = 0;
    @Column(nullable = false)
    private Integer itemPenalidadeDano = 0;

    @Column(nullable = false)
    private Integer itemBonusPv = 0;
    @Column(nullable = false)
    private Integer itemPenalidadePv = 0;

    @Column(nullable = false)
    private Integer itemBonusPm = 0;
    @Column(nullable = false)
    private Integer itemPenalidadePm = 0;

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

    public Integer getItemBonusAtaque() {
        return itemBonusAtaque;
    }

    public void setItemBonusAtaque(Integer itemBonusAtaque) {
        this.itemBonusAtaque = itemBonusAtaque;
    }

    public Integer getItemPenalidadeAtaque() {
        return itemPenalidadeAtaque;
    }

    public void setItemPenalidadeAtaque(Integer itemPenalidadeAtaque) {
        this.itemPenalidadeAtaque = itemPenalidadeAtaque;
    }

    public Integer getItemBonusDano() {
        return itemBonusDano;
    }

    public void setItemBonusDano(Integer itemBonusDano) {
        this.itemBonusDano = itemBonusDano;
    }

    public Integer getItemPenalidadeDano() {
        return itemPenalidadeDano;
    }

    public void setItemPenalidadeDano(Integer itemPenalidadeDano) {
        this.itemPenalidadeDano = itemPenalidadeDano;
    }

    public Integer getItemBonusPv() {
        return itemBonusPv;
    }

    public void setItemBonusPv(Integer itemBonusPv) {
        this.itemBonusPv = itemBonusPv;
    }

    public Integer getItemPenalidadePv() {
        return itemPenalidadePv;
    }

    public void setItemPenalidadePv(Integer itemPenalidadePv) {
        this.itemPenalidadePv = itemPenalidadePv;
    }

    public Integer getItemBonusPm() {
        return itemBonusPm;
    }

    public void setItemBonusPm(Integer itemBonusPm) {
        this.itemBonusPm = itemBonusPm;
    }

    public Integer getItemPenalidadePm() {
        return itemPenalidadePm;
    }

    public void setItemPenalidadePm(Integer itemPenalidadePm) {
        this.itemPenalidadePm = itemPenalidadePm;
    }

    
    
}
