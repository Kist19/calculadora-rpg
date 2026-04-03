package com.arthur.calculadorarpg.efeitoativo;

import com.arthur.calculadorarpg.efeito.Efeito;
import com.arthur.calculadorarpg.personagem.Personagem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_efeito_ativo")
public class EfeitoAtivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "efeito_id", nullable = false)
    private Efeito efeito;

    @ManyToOne(optional = false)
    @JoinColumn(name = "personagem_id", nullable = false)
    @JsonIgnoreProperties({
            "status",
            "atributo",
            "pericia",
            "inventario",
            "habilidades",
            "armaEquipada",
            "armaduraEquipada",
            "escudoEquipado"
    })
    private Personagem personagem;

    @Column(nullable = false)
    private Integer efeitoAtivoTurnoRestante;

    @Column(nullable = false)
    private Boolean efeitoAtivoLigado = true;

    public EfeitoAtivo() {
    }

    public Long getId() {
        return id;
    }

    public Efeito getEfeito() {
        return efeito;
    }

    public void setEfeito(Efeito efeito) {
        this.efeito = efeito;
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    public Integer getEfeitoAtivoTurnoRestante() {
        return efeitoAtivoTurnoRestante;
    }

    public void setEfeitoAtivoTurnoRestante(Integer efeitoAtivoTurnoRestante) {
        this.efeitoAtivoTurnoRestante = efeitoAtivoTurnoRestante;
    }

    public Boolean getEfeitoAtivoLigado() {
        return efeitoAtivoLigado;
    }

    public void setEfeitoAtivoLigado(Boolean efeitoAtivoLigado) {
        this.efeitoAtivoLigado = efeitoAtivoLigado;
    }
}