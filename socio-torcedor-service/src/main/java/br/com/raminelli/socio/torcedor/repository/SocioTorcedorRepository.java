package br.com.raminelli.socio.torcedor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.raminelli.ns.commons.model.SocioTorcedor;

public interface SocioTorcedorRepository extends MongoRepository<SocioTorcedor, String> {
	
	public SocioTorcedor findByEmail(String email);
	
}
