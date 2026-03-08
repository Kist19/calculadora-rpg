package com.arthur.calculadorarpg.inventario;

import jakarta.persistence.*;

import com.arthur.calculadorarpg.personagem.Personagem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.arthur.calculadorarpg.item.Item;

@Entity
@Table(name = "tb_inventario")
public class Inventario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "personagem_id")
	@JsonIgnore
	private Personagem personagem;

	@ManyToOne
	@JoinColumn(name = "item_id", nullable = false)
	private Item item;

	@Column(nullable = false)
	private Integer inventarioQuantidade;

	public Inventario() {
	}

	public Long getId() {
		return id;
	}

	public Personagem getPersonagem() {
		return personagem;
	}

	public void setPersonagem(Personagem personagem) {
		this.personagem = personagem;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Integer getInventarioQuantidade() {
		return inventarioQuantidade;
	}

	public void setInventarioQuantidade(Integer inventarioQuantidade) {
		this.inventarioQuantidade = inventarioQuantidade;
	}

}
