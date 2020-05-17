package com.felipejanser.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.felipejanser.cursomc.domain.Produto;
import com.felipejanser.cursomc.services.validation.ProdutoInsert;

@ProdutoInsert
public class ProdutoNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message="Preenchimento do campo 'Nome' é obrigatório.")
	@Length(min=5,max=120, message="Campo 'Nome' deve ter entre 5 e 120 caracteres.")
	private String nome;
	private Double preco;
	
	private Integer categoriaId;
	
	public ProdutoNewDTO() {

	}
	
	public ProdutoNewDTO(Produto obj) {
		this.nome = obj.getNome();
		this.preco = obj.getPreco();
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

	public Integer getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}

	
}
