package com.felipejanser.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.felipejanser.cursomc.services.validation.ClienteInsert;

@ClienteInsert
public class ClienteNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message="Preenchimento do campo 'Nome' é obrigatório.")
	@Length(min=5,max=120, message="Campo 'Nome' deve ter entre 5 e 120 caracteres.")
	private String nome;
	@NotEmpty(message="Preenchimento do campo 'Email' é obrigatório.")
	@Email(message="Email inválido.")
	private String email;
	@NotEmpty(message="Preenchimento do campo 'CPF/CNPJ' é obrigatório.")
	private String cpf_cnpj;
	private Integer tipo;
	
	@NotEmpty(message="Preenchimento do campo 'Logradouro' é obrigatório.")
	private String logradouro;
	@NotEmpty(message="Preenchimento do campo 'Número' é obrigatório.")
	private String numero;
	private String complemento;
	@NotEmpty(message="Preenchimento do campo 'Bairro' é obrigatório.")
	private String bairro;
	@NotEmpty(message="Preenchimento do campo 'CEP' é obrigatório.")
	private String cep;
	
	@NotEmpty(message="Preenchimento do campo 'Telefone 1' é obrigatório.")
	private String telefone1;
	private String telefone2;
	private String telefone3;
	
	private Integer cidadeId;

	public ClienteNewDTO() {
		
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

	public String getCpf_cnpj() {
		return cpf_cnpj;
	}

	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}
	
}
