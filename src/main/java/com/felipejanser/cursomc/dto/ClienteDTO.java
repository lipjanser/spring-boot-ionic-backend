package com.felipejanser.cursomc.dto;

import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.felipejanser.cursomc.domain.Cliente;
import com.felipejanser.cursomc.services.validation.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento do campo 'Nome' é obrigatório.")
	@Length(min=5,max=120, message="Campo 'Nome' deve ter entre 5 e 120 caracteres.")
	private String nome;
	
	@NotEmpty(message="Preenchimento do campo 'Email' é obrigatório.")
	@Email(message="Email inválido.")
	private String email;
	
	public ClienteDTO() {

	}
	
	public ClienteDTO(Cliente obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
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
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
