package com.arthur.calculadorarpg.personagem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.arthur.calculadorarpg.armadura.Armadura;
import com.arthur.calculadorarpg.arma.Arma;
import com.arthur.calculadorarpg.atributo.Atributo;
import com.arthur.calculadorarpg.habilidade.Habilidade;
import com.arthur.calculadorarpg.inventario.Inventario;
import com.arthur.calculadorarpg.pericia.Pericia;
import com.arthur.calculadorarpg.status.Status;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_personagem")
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String personagemNome;

    @Column(length = 20, nullable = false)
    private String personagemRaca;

    @Column(length = 20, nullable = false)
    private String personagemClasse;

    @Column(length = 30, nullable = false)
    private String personagemOrigem;

    @Column(nullable = false)
    private int personagemNivel;

    @Column(nullable = false)
    private Boolean personagemEmCombate = false;

    @ElementCollection
    @CollectionTable(name = "tb_personagem_item_turno", joinColumns = @JoinColumn(name = "personagem_id"))
    @MapKeyColumn(name = "item_nome")
    @Column(name = "turnos_restantes")
    private Map<String, Integer> itensTurnosRestantesNaCena = new HashMap<>();

    @ElementCollection
    @CollectionTable(name = "tb_personagem_habilidade_turno", joinColumns = @JoinColumn(name = "personagem_id"))
    @MapKeyColumn(name = "habilidade_nome")
    @Column(name = "turnos_restantes")
    private Map<String, Integer> habilidadesTurnosRestantesNaCena = new HashMap<>();

    @OneToOne(mappedBy = "personagem", cascade = CascadeType.ALL)
    private Status status;

    @OneToOne(mappedBy = "personagem", cascade = CascadeType.ALL)
    private Atributo atributo;

    @OneToOne(mappedBy = "personagem", cascade = CascadeType.ALL)
    private Pericia pericia;

    @OneToMany(mappedBy = "personagem", cascade = CascadeType.ALL)
    private List<Inventario> inventarios = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "tb_personagem_habilidade", joinColumns = @JoinColumn(name = "personagem_id"), inverseJoinColumns = @JoinColumn(name = "habilidade_id"))
    private List<Habilidade> habilidades = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "arma_equipada_id", nullable = true)
    private Arma armaEquipada;

    @ManyToOne
    @JoinColumn(name = "armadura_equipada_id", nullable = true)
    private Armadura armaduraEquipada;

    @ManyToOne
    @JoinColumn(name = "escudo_equipado_id", nullable = true)
    private Armadura escudoEquipado;

    public Personagem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonagemNome() {
        return personagemNome;
    }

    public void setPersonagemNome(String personagemNome) {
        this.personagemNome = personagemNome;
    }

    public String getPersonagemRaca() {
        return personagemRaca;
    }

    public void setPersonagemRaca(String personagemRaca) {
        this.personagemRaca = personagemRaca;
    }

    public String getPersonagemClasse() {
        return personagemClasse;
    }

    public void setPersonagemClasse(String personagemClasse) {
        this.personagemClasse = personagemClasse;
    }

    public String getPersonagemOrigem() {
        return personagemOrigem;
    }

    public void setPersonagemOrigem(String personagemOrigem) {
        this.personagemOrigem = personagemOrigem;
    }

    public int getPersonagemNivel() {
        return personagemNivel;
    }

    public void setPersonagemNivel(int personagemNivel) {
        this.personagemNivel = personagemNivel;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Atributo getAtributo() {
        return atributo;
    }

    public void setAtributo(Atributo atributo) {
        this.atributo = atributo;
    }

    public Pericia getPericia() {
        return pericia;
    }

    public void setPericia(Pericia pericia) {
        this.pericia = pericia;
    }

    public List<Habilidade> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<Habilidade> habilidades) {
        this.habilidades = habilidades;
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

    public List<Inventario> getInventarios() {
        return inventarios;
    }

    public void setInventarios(List<Inventario> inventarios) {
        this.inventarios = inventarios;
    }

    public Boolean getPersonagemEmCombate() {
        return personagemEmCombate;
    }

    public void setPersonagemEmCombate(Boolean personagemEmCombate) {
        this.personagemEmCombate = personagemEmCombate;
    }

    public Map<String, Integer> getItensTurnosRestantesNaCena() {
        return itensTurnosRestantesNaCena;
    }

    public void setItensTurnosRestantesNaCena(Map<String, Integer> itensTurnosRestantesNaCena) {
        this.itensTurnosRestantesNaCena = itensTurnosRestantesNaCena;
    }

    public Map<String, Integer> getHabilidadesTurnosRestantesNaCena() {
        return habilidadesTurnosRestantesNaCena;
    }

    public void setHabilidadesTurnosRestantesNaCena(Map<String, Integer> habilidadesTurnosRestantesNaCena) {
        this.habilidadesTurnosRestantesNaCena = habilidadesTurnosRestantesNaCena;
    }
}