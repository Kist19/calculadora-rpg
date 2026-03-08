package com.arthur.calculadorarpg.armadura;

import jakarta.persistence.*;

import com.arthur.calculadorarpg.inventario.Inventario;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_armadura")
public class Armadura {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "inventario_id")
    @JsonIgnore
    private Inventario inventario;

    @Column(length = 30, nullable = false)
    private String armaduraNome;

    @Column(nullable = false)
    private Integer armaduraPreco;

    @Column(nullable = false)
    private Integer armaduraBonusDefesa;

    @Column(nullable = false)
    private Integer armaduraPenalidadeArmadura = 0;

    @Column(nullable = false)
    private Integer armaduraEspaco;

    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private ArmaduraTipo armaduraTipo;

    public Armadura() {
    }

    public Long getId() {
        return id;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public String getArmaduraNome() {
        return armaduraNome;
    }

    public void setArmaduraNome(String armaduraNome) {
        this.armaduraNome = armaduraNome;
    }

    public Integer getArmaduraPreco() {
        return armaduraPreco;
    }

    public void setArmaduraPreco(Integer armaduraPreco) {
        this.armaduraPreco = armaduraPreco;
    }

    public Integer getArmaduraBonusDefesa() {
        return armaduraBonusDefesa;
    }

    public void setArmaduraBonusDefesa(Integer armaduraBonusDefesa) {
        this.armaduraBonusDefesa = armaduraBonusDefesa;
    }

    public Integer getArmaduraPenalidadeArmadura() {
        return armaduraPenalidadeArmadura;
    }

    public void setArmaduraPenalidadeArmadura(Integer armaduraPenalidadeArmadura) {
        this.armaduraPenalidadeArmadura = armaduraPenalidadeArmadura;
    }

    public Integer getArmaduraEspaco() {
        return armaduraEspaco;
    }

    public void setArmaduraEspaco(Integer armaduraEspaco) {
        this.armaduraEspaco = armaduraEspaco;
    }

    public ArmaduraTipo getArmaduraTipo() {
        return armaduraTipo;
    }

    public void setArmaduraTipo(ArmaduraTipo armaduraTipo) {
        this.armaduraTipo = armaduraTipo;
    }
    

}