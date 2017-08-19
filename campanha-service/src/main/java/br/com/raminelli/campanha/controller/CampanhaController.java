package br.com.raminelli.campanha.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.raminelli.campanha.legacy.NotifyLegacy;
import br.com.raminelli.campanha.repository.CampanhaRepository;
import br.com.raminelli.ns.commons.model.Campanha;
import br.com.raminelli.ns.commons.utils.DateUtils;

@RestController
public class CampanhaController {

	@Autowired
	private CampanhaRepository campanhaRepository;
	@Autowired
	private NotifyLegacy notifyLegacy;
	
	@GetMapping("/campanhas")
	public List<Campanha> listAll() {
		//Não retorna campanhas com a data de vigência vencidas
		return campanhaRepository.findByDataTerminoGreaterThanEqual(new Date());
	}
	
	@PostMapping("/campanhas")
	public Campanha insert(@RequestBody Campanha campanha) {
		//Verifica se existem campanhas no mesmo periodo de vigencia
		final List<Campanha> campanhasMesmaVigencia = 
				campanhaRepository.findByDataInicioAndDataTerminoLessThanEqual(campanha.getDataInicio(), campanha.getDataTermino());
		
		verificarCampanhasMesmaVigencia(campanha, campanhasMesmaVigencia); //valida campanhas com a mesma data de vigencia
		campanhaRepository.save(campanhasMesmaVigencia); //atualiza as novas datas
		
		return campanhaRepository.insert(campanha);
	}
	
	@PutMapping("/campanhas/{id}")
	public Campanha update(@PathVariable String id, @RequestBody Campanha campanha) {
		campanha = campanhaRepository.save(campanha);
		notifyLegacy.notifyUpdateCampanha(campanha); //Notifica legados sobre a alteração da campanha
		return 	campanha;	
	}
	
	@DeleteMapping("/campanhas/{id}")
	public void delete(@PathVariable String id) {
		campanhaRepository.delete(id);		
	}
	
	@GetMapping("/campanhas/{id}")
	public Campanha findById(@PathVariable String id) {
		return campanhaRepository.findOne(id);		
	}
		
	private static void verificarCampanhasMesmaVigencia(final Campanha campanhaNova, final List<Campanha> campanhas) {
		for (Campanha campanhaData : campanhas) { //acrescenta um dia na data até ela não se repetir
			do {
				campanhaData.setDataTermino(DateUtils.addDays(campanhaData.getDataTermino(), 1));
			} while (possuiCampanhaMesmaDataTermino(campanhaNova, campanhaData, campanhas));
		}
	}
	
	private static  boolean possuiCampanhaMesmaDataTermino(final Campanha campanhaNova, final Campanha campanhaData, final List<Campanha> campanhas) {
		if(DateUtils.dateEquals(campanhaData.getDataTermino(), campanhaNova.getDataTermino())) {
			return true;
		}
		
		for (Campanha campanha : campanhas) {
			if(!campanha.getId().equals(campanhaData.getId()) 
					&& DateUtils.dateEquals(campanhaData.getDataTermino(), campanha.getDataTermino())) {
				return true;
			}
		}
		return false;
	}
	
}
