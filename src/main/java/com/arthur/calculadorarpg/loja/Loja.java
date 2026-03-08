package com.arthur.calculadorarpg.loja;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_loja")
public class Loja {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 30, nullable = false)
	private String lojaNome;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private LojaTipoLoja lojaTipo;

	@Column(nullable = false)
	private Boolean ativa = true;

	public Loja() {

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

	public Boolean getAtiva() {
		return ativa;
	}

	public void setAtiva(Boolean ativa) {
		this.ativa = ativa;
	}

	public LojaTipoLoja getLojaTipo() {
		return lojaTipo;
	}

	public void setLojaTipo(LojaTipoLoja lojaTipo) {
		this.lojaTipo = lojaTipo;
	}
	
}
