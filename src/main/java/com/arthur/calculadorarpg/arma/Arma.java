package com.arthur.calculadorarpg.arma;

import jakarta.persistence.*;

import com.arthur.calculadorarpg.inventario.Inventario;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_arma")
public class Arma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "inventario_id")
    @JsonIgnore
    private Inventario inventario;

    @Column(length = 30, nullable = true, unique = false)
    private String armaNome;

    @Column(length = 10, nullable = true, unique = false)
    private String armaTesteAtaque = "1d20";

    private Integer armaBonusTesteAtaque = 0;
    private Integer armaPenalidadeTesteAtaque = 0;

    @Column(nullable = true)
    private Integer armaQuantidadeDado = 1;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = true, unique = false)
    private ArmaTipoDado armaTipoDado;

    private Integer armaBonusDano = 0;
    private Integer armaPenalidadeDano = 0;

    private Integer armaCriticoMinimo;

    private Integer armaCriticoMultiplicador;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = true, unique = false)
    private ArmaTipoAlcance armaAlcance;

    @Enumerated(EnumType.STRING)
    @Column(length = 15, nullable = true, unique = false)
    private ArmaTipoArma armaTipo;

    private Integer armaEspaco;

    @Column(nullable = true)
    private Integer armaPreco;

    private Integer armaBonusPv = 0;
    private Integer armaPenalidadePv = 0;

    private Integer armaBonusPm = 0;
    private Integer armaPenalidadePm = 0;

    public Arma(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public String getArmaNome() {
        return armaNome;
    }

    public void setArmaNome(String armaNome) {
        this.armaNome = armaNome;
    }

    public String getArmaTesteAtaque() {
        return armaTesteAtaque;
    }

    public void setArmaTesteAtaque(String armaTesteAtaque) {
        this.armaTesteAtaque = armaTesteAtaque;
    }

    public Integer getArmaBonusTesteAtaque() {
        return armaBonusTesteAtaque;
    }

    public void setArmaBonusTesteAtaque(Integer armaBonusTesteAtaque) {
        this.armaBonusTesteAtaque = armaBonusTesteAtaque;
    }

    public Integer getArmaPenalidadeTesteAtaque() {
        return armaPenalidadeTesteAtaque;
    }

    public void setArmaPenalidadeTesteAtaque(Integer armaPenalidadeTesteAtaque) {
        this.armaPenalidadeTesteAtaque = armaPenalidadeTesteAtaque;
    }

    public Integer getArmaQuantidadeDados() {
        return armaQuantidadeDado;
    }

    public void setArmaQuantidadeDados(Integer armaQuantidadeDados) {
        this.armaQuantidadeDado = armaQuantidadeDados;
    }

    public ArmaTipoDado getArmaTipoDado() {
        return armaTipoDado;
    }

    public void setArmaTipoDado(ArmaTipoDado armaTipoDado) {
        this.armaTipoDado = armaTipoDado;
    }

    public Integer getArmaBonusDano() {
        return armaBonusDano;
    }

    public void setArmaBonusDano(Integer armaBonusDano) {
        this.armaBonusDano = armaBonusDano;
    }

    public Integer getArmaPenalidadeDano() {
        return armaPenalidadeDano;
    }

    public void setArmaPenalidadeDano(Integer armaPenalidadeDano) {
        this.armaPenalidadeDano = armaPenalidadeDano;
    }

    public Integer getArmaCriticoMinimo() {
        return armaCriticoMinimo;
    }

    public void setArmaCriticoMinimo(Integer armaCriticoMinimo) {
        this.armaCriticoMinimo = armaCriticoMinimo;
    }

    public Integer getArmaCriticoMultiplicador() {
        return armaCriticoMultiplicador;
    }

    public void setArmaCriticoMultiplicador(Integer armaCriticoMultiplicador) {
        this.armaCriticoMultiplicador = armaCriticoMultiplicador;
    }

    public ArmaTipoAlcance getArmaAlcance() {
        return armaAlcance;
    }

    public void setArmaAlcance(ArmaTipoAlcance armaAlcance) {
        this.armaAlcance = armaAlcance;
    }

    public ArmaTipoArma getArmaTipo() {
        return armaTipo;
    }

    public void setArmaTipo(ArmaTipoArma armaTipo) {
        this.armaTipo = armaTipo;
    }

    public Integer getArmaEspaco() {
        return armaEspaco;
    }

    public void setArmaEspaco(Integer armaEspaco) {
        this.armaEspaco = armaEspaco;
    }

    public Integer getArmaQuantidadeDado() {
        return armaQuantidadeDado;
    }

    public void setArmaQuantidadeDado(Integer armaQuantidadeDado) {
        this.armaQuantidadeDado = armaQuantidadeDado;
    }

    public Integer getArmaPreco() {
        return armaPreco;
    }

    public void setArmaPreco(Integer armaPreco) {
        this.armaPreco = armaPreco;
    }

    public Integer getArmaBonusPv() {
        return armaBonusPv;
    }

    public void setArmaBonusPv(Integer armaBonusPv) {
        this.armaBonusPv = armaBonusPv;
    }

    public Integer getArmaPenalidadePv() {
        return armaPenalidadePv;
    }

    public void setArmaPenalidadePv(Integer armaPenalidadePv) {
        this.armaPenalidadePv = armaPenalidadePv;
    }

    public Integer getArmaBonusPm() {
        return armaBonusPm;
    }

    public void setArmaBonusPm(Integer armaBonusPm) {
        this.armaBonusPm = armaBonusPm;
    }

    public Integer getArmaPenalidadePm() {
        return armaPenalidadePm;
    }

    public void setArmaPenalidadePm(Integer armaPenalidadePm) {
        this.armaPenalidadePm = armaPenalidadePm;
    }
    
    @Transient
    public String getDanoFormatado(){
        if (armaQuantidadeDado == null || armaTipoDado ==null) {
            return null;
        }
        String dano =  armaQuantidadeDado + "d" + armaTipoDado.name().substring(1);

        if (armaBonusDano != null && armaBonusDano >0) {
            dano += " + " + armaBonusDano;
        }

        if (armaPenalidadeDano != null && armaPenalidadeDano >0) {
            dano += " - " + armaPenalidadeDano;
            
        }

        return dano;
    }
}
