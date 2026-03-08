package com.arthur.calculadorarpg.habilidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_habilidade")
public class Habilidade {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String habilidadeNome;

    @Column(nullable = false)
    private Integer habilidadeCustoPm;

    @Column(nullable = false)
    private Integer habilidadeBonusAtaque;

    @Column(nullable = false)
    private Integer habilidadeBonusDano;

    @Column(nullable = false)
    private Integer habilidadeBonusPv;

    @Column(nullable = false)
    private Integer habilidadeBonusPm;

    @Column(nullable = false)
    private Integer habilidadePenalidadeAtaque;

    @Column(nullable = false)
    private Integer habilidadePenalidadeDano;

    public Habilidade(){

    }

    public Long Id(){
        return id;
    }

    public String getHabilidadeNome() {
        return habilidadeNome;
    }

    public void setHabilidadeNome(String habilidadeNome) {
        this.habilidadeNome = habilidadeNome;
    }

    public Integer getHabilidadeCustoPm() {
        return habilidadeCustoPm;
    }

    public void setHabilidadeCustoPm(Integer habilidadeCustoPm) {
        this.habilidadeCustoPm = habilidadeCustoPm;
    }

    public Integer getHabilidadeBonusAtaque() {
        return habilidadeBonusAtaque;
    }

    public void setHabilidadeBonusAtaque(Integer habilidadeBonusAtaque) {
        this.habilidadeBonusAtaque = habilidadeBonusAtaque;
    }

    public Integer getHabilidadeBonusDano() {
        return habilidadeBonusDano;
    }

    public void setHabilidadeBonusDano(Integer habilidadeBonusDano) {
        this.habilidadeBonusDano = habilidadeBonusDano;
    }

    public Integer getHabilidadeBonusPv() {
        return habilidadeBonusPv;
    }

    public void setHabilidadeBonusPv(Integer habilidadeBonusPv) {
        this.habilidadeBonusPv = habilidadeBonusPv;
    }

    public Integer getHabilidadeBonusPm() {
        return habilidadeBonusPm;
    }

    public void setHabilidadeBonusPm(Integer habilidadeBonusPm) {
        this.habilidadeBonusPm = habilidadeBonusPm;
    }

    public Integer getHabilidadePenalidadeAtaque() {
        return habilidadePenalidadeAtaque;
    }

    public void setHabilidadePenalidadeAtaque(Integer habilidadePenalidadeAtaque) {
        this.habilidadePenalidadeAtaque = habilidadePenalidadeAtaque;
    }

    public Integer getHabilidadePenalidadeDano() {
        return habilidadePenalidadeDano;
    }

    public void setHabilidadePenalidadeDano(Integer habilidadePenalidadeDano) {
        this.habilidadePenalidadeDano = habilidadePenalidadeDano;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
