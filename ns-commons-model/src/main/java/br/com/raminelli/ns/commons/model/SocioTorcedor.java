package br.com.raminelli.ns.commons.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document
public class SocioTorcedor {

	@Id
	private String id;
	private String nome;
	private String email;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dataNascimento;
	@DBRef
	private Time timeCoracao = new Time();
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Time getTimeCoracao() {
		return timeCoracao;
	}
	public void setTimeCoracao(Time timeCoracao) {
		this.timeCoracao = timeCoracao;
	}
	
}
