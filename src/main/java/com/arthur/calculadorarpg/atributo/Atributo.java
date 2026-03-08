package com.arthur.calculadorarpg.atributo;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.arthur.calculadorarpg.personagem.Personagem;

@Entity
@Table(name = "tb_atributo")
public class Atributo {

    @Id
    private Long id;

    @JsonIgnoreProperties({"status", "atributo", "pericia", "inventario", "habilidades", "armaEquipada", "armaduraEquipada"})
    @OneToOne
    @MapsId
    @JoinColumn(name = "personagem_id", nullable = false)
    private Personagem personagem;

    @Column(nullable = false)
    private Integer atributoForca;

    @Column(nullable = false)
    private Integer atributoDestreza;

    @Column(nullable = false)
    private Integer atributoConstituicao;

    @Column(nullable = false)
    private Integer atributoInteligencia;

    @Column(nullable = false)
    private Integer atributoSabedoria;

    @Column(nullable = false)
    private Integer atributoCarisma;

    public Atributo() {

    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    

    public Integer getAtributoForca() {
        return atributoForca;
    }

    public void setAtributoForca(int atributoForca) {
        this.atributoForca = atributoForca;
    }

    public Integer getAtributoDestreza() {
        return atributoDestreza;
    }

    public void setAtributoDestreza(int atributoDestreza) {
        this.atributoDestreza = atributoDestreza;
    }

    public Integer getAtributoConstituicao() {
        return atributoConstituicao;
    }

    public void setAtributoConstituicao(int atributoConstituicao) {
        this.atributoConstituicao = atributoConstituicao;
    }

    public Integer getAtributoInteligencia() {
        return atributoInteligencia;
    }

    public void setAtributoInteligencia(int atributoInteligencia) {
        this.atributoInteligencia = atributoInteligencia;
    }

    public Integer getAtributoSabedoria() {
        return atributoSabedoria;
    }

    public void setAtributoSabedoria(int atributoSabedoria) {
        this.atributoSabedoria = atributoSabedoria;
    }

    public Integer getAtributoCarisma() {
        return atributoCarisma;
    }

    public void setAtributoCarisma(int atributoCarisma) {
        this.atributoCarisma = atributoCarisma;
    }

    public Long getId() {
        return id;
    }
}
