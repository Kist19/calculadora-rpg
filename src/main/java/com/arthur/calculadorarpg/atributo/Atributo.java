package com.arthur.calculadorarpg.atributo;

import jakarta.persistence.*;

import com.arthur.calculadorarpg.personagem.Personagem;

@Entity
@Table(name = "tb_atributo")
public class Atributo {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "personagem_id")
    private Personagem personagem;

    @Column(nullable = false, unique = false)
    private int atributoForca;

    @Column(nullable = false, unique = false)
    private int atributoDestreza;

    @Column(nullable = false, unique = false)
    private int atributoConstituicao;

    @Column(nullable = false, unique = false)
    private int atributoInteligencia;

    @Column(nullable = false, unique = false)
    private int atributoSabedoria;

    @Column(nullable = false, unique = false)
    private int atributoCarisma;

    public Atributo() {

    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    

    public int getAtributoForca() {
        return atributoForca;
    }

    public void setAtributoForca(int atributoForca) {
        this.atributoForca = atributoForca;
    }

    public int getAtributoDestreza() {
        return atributoDestreza;
    }

    public void setAtributoDestreza(int atributoDestreza) {
        this.atributoDestreza = atributoDestreza;
    }

    public int getAtributoConstituicao() {
        return atributoConstituicao;
    }

    public void setAtributoConstituicao(int atributoConstituicao) {
        this.atributoConstituicao = atributoConstituicao;
    }

    public int getAtributoInteligencia() {
        return atributoInteligencia;
    }

    public void setAtributoInteligencia(int atributoInteligencia) {
        this.atributoInteligencia = atributoInteligencia;
    }

    public int getAtributoSabedoria() {
        return atributoSabedoria;
    }

    public void setAtributoSabedoria(int atributoSabedoria) {
        this.atributoSabedoria = atributoSabedoria;
    }

    public int getAtributoCarisma() {
        return atributoCarisma;
    }

    public void setAtributoCarisma(int atributoCarisma) {
        this.atributoCarisma = atributoCarisma;
    }

    public Long getId() {
        return id;
    }
}
