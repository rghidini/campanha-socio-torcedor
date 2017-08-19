package br.com.raminelli.campanha.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.raminelli.ns.commons.model.SocioTorcedor;
import br.com.raminelli.ns.commons.model.SocioTorcedorCampanha;

public interface SocioTorcedorCampanhaRepository extends MongoRepository<SocioTorcedorCampanha, String> {
	
	public List<SocioTorcedorCampanha> findBySocioTorcedor(SocioTorcedor socioTorcedor);
	
}
