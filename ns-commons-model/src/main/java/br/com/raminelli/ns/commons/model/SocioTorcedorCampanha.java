package br.com.raminelli.ns.commons.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class SocioTorcedorCampanha {

	@Id
	private String id;
	@DBRef
	private SocioTorcedor socioTorcedor;
	@DBRef
	private Campanha campanha;
	
	public SocioTorcedorCampanha() {
		
	}
	
	public SocioTorcedorCampanha(SocioTorcedor socioTorcedor, Campanha campanha) {
		this.socioTorcedor = socioTorcedor;
		this.campanha = campanha;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public SocioTorcedor getSocioTorcedor() {
		return socioTorcedor;
	}
	public void setSocioTorcedor(SocioTorcedor socioTorcedor) {
		this.socioTorcedor = socioTorcedor;
	}
	public Campanha getCampanha() {
		return campanha;
	}
	public void setCampanha(Campanha campanha) {
		this.campanha = campanha;
	}
	
}
