package br.com.techne.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 2475132382492676003L;
	
	@Id
	@GeneratedValue
	private String id;
	
	@NotNull(message="O Nome deve ser preenchido")
	@NotBlank(message="O Nome deve ser preenchido")
	private String nome;
	
	@NotNull(message="O cpf deve ser preenchido")
	@NotBlank(message="O cpf deve ser preenchido")
	private String cpf;
	
	private int idade;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	
}
