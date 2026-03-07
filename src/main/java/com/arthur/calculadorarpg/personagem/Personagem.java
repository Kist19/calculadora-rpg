package com.arthur.calculadorarpg.personagem;

import jakarta.persistence.*;

import com.arthur.calculadorarpg.atributo.Atributo;
import com.arthur.calculadorarpg.pericia.Pericia;
import com.arthur.calculadorarpg.status.Status;
import com.arthur.calculadorarpg.arma.Arma;
import com.arthur.calculadorarpg.armadura.Armadura;

@Entity
@Table(name = "tb_personagem")
public class Personagem{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomePersonagem;

    @ManyToOne
    @JoinColumn(name = "arma_equipada_id")
    private Arma armaEquipada;

    @ManyToOne
    @JoinColumn(name = "armadura_equipada_id")
    private Armadura armaduraEquipada;

    @ManyToOne
    @JoinColumn(name = "escudo_equipado_id")
    private Armadura escudoEquipado;

    @Column(length = 20, nullable = false, unique = false)
    private String racaPersonagem;

    @Column(length = 20, nullable = false, unique = false)
    private String classePersonagem;

    @Column(length = 30, nullable = false, unique = false)
    private String origemPersonagem;

    @Column(nullable = false, unique = false)
    private int nivelPersonagem;

    public Personagem(){

    }

    @OneToOne(mappedBy = "personagem", cascade = CascadeType.ALL)
    private Atributo atributo;

    @OneToOne(mappedBy = "personagem", cascade = CascadeType.ALL)
    private Pericia pericia;

    @OneToOne(mappedBy = "personagem", cascade = CascadeType.ALL)
    private Status status;

    public Atributo getAtributo() {
        return atributo;
    }

    public Pericia getPericia() {
        return pericia;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }



    public Long getId(){
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNomePersonagem() {
        return nomePersonagem;
    }

    public void setNomePersonagem(String nomePersonagem) {
        this.nomePersonagem = nomePersonagem;
    }

    public String getRacaPersonagem() {
        return racaPersonagem;
    }

    public void setRacaPersonagem(String racaPersonagem) {
        this.racaPersonagem = racaPersonagem;
    }

    public String getClassePersonagem() {
        return classePersonagem;
    }

    public void setClassePersonagem(String classePersonagem) {
        this.classePersonagem = classePersonagem;
    }

    public String getOrigemPersonagem() {
        return origemPersonagem;
    }

    public void setOrigemPersonagem(String origemPersonagem) {
        this.origemPersonagem = origemPersonagem;
    }

    public int getNivelPersonagem() {
        return nivelPersonagem;
    }

    public void setNivelPersonagem(int nivelPersonagem) {
        this.nivelPersonagem = nivelPersonagem;
    }

    public Arma getArmaEquipada() {
        return armaEquipada;
    }

    public void setArmaEquipada(Arma armaEquipada) {
        this.armaEquipada = armaEquipada;
    }

    public Armadura getArmaduraEquipada() {
        return armaduraEquipada;
    }

    public void setArmaduraEquipada(Armadura armaduraEquipada) {
        this.armaduraEquipada = armaduraEquipada;
    }

    public Armadura getEscudoEquipado() {
        return escudoEquipado;
    }

    public void setEscudoEquipado(Armadura escudoEquipado) {
        this.escudoEquipado = escudoEquipado;
    }
    
}