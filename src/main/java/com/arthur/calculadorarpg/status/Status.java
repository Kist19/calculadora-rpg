package com.arthur.calculadorarpg.status;

import jakarta.persistence.*;

import com.arthur.calculadorarpg.personagem.Personagem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_status")
public class Status {
    @Id
    private Long id;

    @JsonIgnoreProperties({ "status", "atributo", "pericia", "inventario", "habilidades", "armaEquipada", "armaduraEquipada" })
    @OneToOne
    @MapsId
    @JoinColumn(name = "personagem_id", nullable = false)
    private Personagem personagem;

    @Column(nullable = true)
    private Integer statusVidaBase;

    @Column(nullable = true)
    private Integer statusVidaAtual;

    @Column(nullable = true)
    private Integer statusManaBase;

    @Column(nullable = true)
    private Integer statusManaAtual;

    @Column(nullable = true)
    private Integer statusDefesa;

    @Column(nullable = true)
    private Integer statusDinheiro;

    @Column(nullable = true)
    private Double statusEspacoBase;

    @Column(nullable = true)
    private Double statusEspacoAtual;

    public Status() {

    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    public Integer getStatusVidaBase() {
        return statusVidaBase;
    }

    public void setStatusVidaBase(Integer statusVidaBase) {
        this.statusVidaBase = statusVidaBase;
    }

    public Integer getStatusVidaAtual() {
        return statusVidaAtual;
    }

    public void setStatusVidaAtual(Integer statusVidaAtual) {
        this.statusVidaAtual = statusVidaAtual;
    }

    public Integer getStatusManaBase() {
        return statusManaBase;
    }

    public void setStatusManaBase(Integer statusManaBase) {
        this.statusManaBase = statusManaBase;
    }

    public Integer getStatusManaAtual() {
        return statusManaAtual;
    }

    public void setStatusManaAtual(Integer statusManaAtual) {
        this.statusManaAtual = statusManaAtual;
    }

    public Integer getStatusDefesa() {
        return statusDefesa;
    }

    public void setStatusDefesa(Integer statusDefesa) {
        this.statusDefesa = statusDefesa;
    }

    public Integer getStatusDinheiro() {
        return statusDinheiro;
    }

    public void setStatusDinheiro(Integer statusDinheiro) {
        this.statusDinheiro = statusDinheiro;
    }

    public Double getStatusEspacoBase() {
        return statusEspacoBase;
    }

    public void setStatusEspacoBase(Double statusEspacoBase) {
        this.statusEspacoBase = statusEspacoBase;
    }

    public Double getStatusEspacoAtual() {
        return statusEspacoAtual;
    }

    public void setStatusEspacoAtual(Double statusEspacoAtual) {
        this.statusEspacoAtual = statusEspacoAtual;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
