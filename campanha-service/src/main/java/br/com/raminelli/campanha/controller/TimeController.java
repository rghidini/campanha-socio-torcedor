package br.com.raminelli.campanha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.raminelli.campanha.repository.TimeRepository;
import br.com.raminelli.ns.commons.model.Time;

@RestController
public class TimeController {

	@Autowired
	private TimeRepository timeRepository;
	
	@GetMapping("/times")
	public List<Time> listAll() {
		return timeRepository.findAll();
	}
	
	@GetMapping("/times/{id}")
	public Time getById(@PathVariable String id) {
		return timeRepository.findOne(id);
	}
	
	@PostMapping("/times")
	public Time insert(@RequestBody Time time) {
		return timeRepository.insert(time);
	}
	
}
