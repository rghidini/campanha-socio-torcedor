package br.com.raminelli.campanha.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.raminelli.campanha.repository.CampanhaRepository;
import br.com.raminelli.campanha.repository.SocioTorcedorCampanhaRepository;
import br.com.raminelli.ns.commons.model.Campanha;
import br.com.raminelli.ns.commons.model.SocioTorcedor;
import br.com.raminelli.ns.commons.model.SocioTorcedorCampanha;
import br.com.raminelli.ns.commons.model.Time;

/**
 * Classe responsavel por realizar a associacao entre as Campanhas e o Socio Torcedor
 * 
 * @author raminelli
 *
 */

@RestController
public class SocioTorcedorCampanhaController {

	@Autowired
	private SocioTorcedorCampanhaRepository socioTorcedorCampanhaRepository;
	@Autowired
	private CampanhaRepository campanhaRepository;
	
	@PutMapping("/associa-socio-campanha/{idSocio}/{idTimeCoracao}")
	public List<Campanha> addSocioCampanha(@PathVariable String idSocio, @PathVariable String idTimeCoracao) {
		
		final SocioTorcedor socioTorcedor = new SocioTorcedor();
		socioTorcedor.setId(idSocio);
		socioTorcedor.setTimeCoracao(new Time());
		socioTorcedor.getTimeCoracao().setId(idTimeCoracao);
		
		//Recupera as campanhas do time do coracao do socio e que nao estejam vencidas
		final List<Campanha> campanhasPorTime = 
				campanhaRepository.findByTimeCoracaoAndDataTerminoGreaterThanEqual(socioTorcedor.getTimeCoracao(), new Date());
		
		//Recupera as campanhas ja cadastradas para o Socio
		final List<Campanha> campanhasSocio = 
				getCampanhasSocio(socioTorcedorCampanhaRepository.findBySocioTorcedor(socioTorcedor));
		
		//Filtra as campanhas que o socio ainda nao possui
		final List<Campanha> novasCampanhas = campanhasPorTime.stream().
				filter(c -> !campanhasSocio.contains(c)).collect(Collectors.toList());
		
		//cria a nova lista SocioTorcedor x Campanha
		final List<SocioTorcedorCampanha> listaSocioCampanhaNova = new ArrayList<>();
		novasCampanhas.forEach(campanha -> listaSocioCampanhaNova.add(new SocioTorcedorCampanha(socioTorcedor, campanha)));
				
		return getCampanhasSocio(socioTorcedorCampanhaRepository.save(listaSocioCampanhaNova));
	}
	
	@GetMapping("/campanhas/socio/{idSocio}")
	public List<Campanha> getById(@PathVariable String idSocio) {
		final SocioTorcedor socio = new SocioTorcedor();
		socio.setId(idSocio);
		return getCampanhasSocio(socioTorcedorCampanhaRepository.findBySocioTorcedor(socio));
	}
	
	private List<Campanha> getCampanhasSocio(final List<SocioTorcedorCampanha> socioCampanhas) {
		if(socioCampanhas == null) {
			return new ArrayList<>();
		}
		
		final List<Campanha> campanhas = new ArrayList<>();
		socioCampanhas.forEach(sc -> campanhas.add(sc.getCampanha()));
		return campanhas;
	}
}
