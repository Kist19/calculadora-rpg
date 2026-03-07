package com.arthur.calculadorarpg.loja;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_loja")
public class Loja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private String lojaNome;

	private String lojaTipo;

	private Boolean ativa = true;

    public Loja(){

    }

    public Long getId() {
        return id;
    }

	public String getLojaNome() {
		return lojaNome;
	}

	public void setLojaNome(String lojaNome) {
		this.lojaNome = lojaNome;
	}

	public String getLojaTipo() {
		return lojaTipo;
	}

	public void setLojaTipo(String lojaTipo) {
		this.lojaTipo = lojaTipo;
	}

	public Boolean getAtiva() {
		return ativa;
	}

	public void setAtiva(Boolean ativa) {
		this.ativa = ativa;
	}
    
}
