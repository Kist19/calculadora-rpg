package com.arthur.calculadorarpg.status;

import jakarta.persistence.*;

import com.arthur.calculadorarpg.personagem.Personagem;

@Entity
@Table(name = "tb_status")
public class Status {
    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "personagem_id")
    private Personagem personagem;

    private Integer vidaBase;
    private Integer vidaAtual;

    private Integer manaBase;
    private Integer manaAtual;

    private Integer defesa;

    private Integer dinheiro;

    private Double espacoBase;
    private Double espacoAtual;

    public Status() {

    }
    
    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    public Integer getVidaBase() {
        return vidaBase;
    }

    public void setVidaBase(Integer vidaBase) {
        this.vidaBase = vidaBase;
    }

    public Integer getVidaAtual() {
        return vidaAtual;
    }

    public void setVidaAtual(Integer vidaAtual) {
        this.vidaAtual = vidaAtual;
    }

    public Integer getManaBase() {
        return manaBase;
    }

    public void setManaBase(Integer manaBase) {
        this.manaBase = manaBase;
    }

    public Integer getManaAtual() {
        return manaAtual;
    }

    public void setManaAtual(Integer manaAtual) {
        this.manaAtual = manaAtual;
    }

    public Integer getDefesa() {
        return defesa;
    }

    public void setDefesa(Integer defesa) {
        this.defesa = defesa;
    }

    public Integer getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(Integer dinheiro) {
        this.dinheiro = dinheiro;
    }

    public Double getEspacoBase() {
        return espacoBase;
    }

    public void setEspacoBase(Double espacoBase) {
        this.espacoBase = espacoBase;
    }

    public Double getEspacoAtual() {
        return espacoAtual;
    }

    public void setEspacoAtual(Double espacoAtual) {
        this.espacoAtual = espacoAtual;
    }

}
