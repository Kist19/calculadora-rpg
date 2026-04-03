package com.arthur.calculadorarpg.item;

import com.arthur.calculadorarpg.arma.ArmaTipoDado;
import com.arthur.calculadorarpg.efeito.Efeito;

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

    @Column(nullable = true)
    private Integer itemDuracaoTurno;

    @Column(nullable = false)
    private Double itemEspaco = 0.0;

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

    @ManyToOne
    @JoinColumn(name = "efeito_id", nullable = true)
    private Efeito efeito;

    @Column(nullable = true)
    private Integer itemQuantidadeDado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true, length = 10)
    private ArmaTipoDado itemTipoDado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true, length = 10)
    private DadoTipoAcao itemDadoTipoAcao;

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

    public Integer getItemDuracaoTurno() {
        return itemDuracaoTurno;
    }

    public void setItemDuracaoTurno(Integer itemDuracaoTurno) {
        this.itemDuracaoTurno = itemDuracaoTurno;
    }

    public Efeito getEfeito() {
        return efeito;
    }

    public void setEfeito(Efeito efeito) {
        this.efeito = efeito;
    }

    public Double getItemEspaco() {
        return itemEspaco;
    }

    public void setItemEspaco(Double itemEspaco) {
        this.itemEspaco = itemEspaco;
    }

    public Integer getItemQuantidadeDado() {
        return itemQuantidadeDado;
    }

    public void setItemQuantidadeDado(Integer itemQuantidadeDado) {
        this.itemQuantidadeDado = itemQuantidadeDado;
    }

    public ArmaTipoDado getItemTipoDado() {
        return itemTipoDado;
    }

    public void setItemTipoDado(ArmaTipoDado itemTipoDado) {
        this.itemTipoDado = itemTipoDado;
    }

    public DadoTipoAcao getItemDadoTipoAcao() {
        return itemDadoTipoAcao;
    }

    public void setItemDadoTipoAcao(DadoTipoAcao itemDadoTipoAcao) {
        this.itemDadoTipoAcao = itemDadoTipoAcao;
    }

}
