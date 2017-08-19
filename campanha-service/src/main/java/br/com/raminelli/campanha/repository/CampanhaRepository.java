package br.com.raminelli.campanha.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.raminelli.ns.commons.model.Campanha;
import br.com.raminelli.ns.commons.model.Time;

public interface CampanhaRepository extends MongoRepository<Campanha, String> {
	
	public List<Campanha> findByDataTerminoGreaterThanEqual(Date data);
	
	public List<Campanha> findByDataInicioBetween(Date dataInicioDe, Date dataInicioAte);
	
	public List<Campanha> findByTimeCoracaoAndDataTerminoGreaterThanEqual(Time time, Date data);
	
	public List<Campanha> findByDataInicioAndDataTerminoLessThanEqual(Date dataInicio, Date dataTermino);
	
}
