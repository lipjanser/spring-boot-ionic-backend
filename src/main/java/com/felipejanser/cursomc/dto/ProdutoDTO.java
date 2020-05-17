package com.felipejanser.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.felipejanser.cursomc.domain.Produto;
import com.felipejanser.cursomc.services.validation.ProdutoUpdate;

@ProdutoUpdate
public class ProdutoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento do campo 'Nome' é obrigatório.")
	@Length(min=5,max=120, message="Campo 'Nome' deve ter entre 5 e 120 caracteres.")
	private String nome;
	private Double preco;
	
	public ProdutoDTO() {

	}
	
	public ProdutoDTO(Produto obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.preco = obj.getPreco();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

}
