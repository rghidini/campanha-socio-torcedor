package br.com.raminelli.campanha.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.raminelli.ns.commons.model.Time;

public interface TimeRepository extends MongoRepository<Time, String> {
	
}
