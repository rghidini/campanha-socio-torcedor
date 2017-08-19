package br.com.raminelli.socio.torcedor.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.raminelli.ns.commons.model.Campanha;
import br.com.raminelli.ns.commons.model.SocioTorcedor;
import br.com.raminelli.socio.torcedor.utils.Constants;
import br.com.raminelli.socio.torcedor.utils.PropertiesConfigLoader;

/**
 * Classe responsavel por realizar a conexao com a API de Campanhas
 * 
 * @author rraminelli
 *
 */

@Service
public class CampanhaService {

	private RestTemplate restTemplate = new RestTemplate();
	
	public List<Campanha> findBySocioTorcedor(final SocioTorcedor socioTorcedor) {
		List<Campanha> campanhas = new ArrayList<>();
		
		final String url = PropertiesConfigLoader.getInstance().getValue(Constants.URL_SERVER_CAMPANHAS) + 
				Constants.PATH_CAMPANHAS_SOCIO + "/" +socioTorcedor.getId();
		
		//Busca as campanhas do time ativas via API		
		final String response = restTemplate.getForObject(url, String.class);		
		try {
			ObjectMapper mapper = new ObjectMapper();
			campanhas = mapper.readValue(response, 
			        mapper.getTypeFactory().constructCollectionType(List.class, Campanha.class));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return campanhas;
	}
	
	public List<Campanha> findAll() {
		List<Campanha> campanhas = new ArrayList<>();
		
		final String url = PropertiesConfigLoader.getInstance().getValue(Constants.URL_SERVER_CAMPANHAS) + Constants.PATH_CAMPANHAS;
		
		//busca as campanhas ativas via API
		final String response = restTemplate.getForObject(url, String.class);		
		try {
			ObjectMapper mapper = new ObjectMapper();
			campanhas = mapper.readValue(response, 
			        mapper.getTypeFactory().constructCollectionType(List.class, Campanha.class));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return campanhas;
	}
	
	public void addSocioTorcedorCampanha(SocioTorcedor socio) {
		if(socio == null || socio.getId() == null ||
				socio.getTimeCoracao() == null || socio.getTimeCoracao().getId() == null) {
			return;
		}
		
		final String url = PropertiesConfigLoader.getInstance().getValue(Constants.URL_SERVER_CAMPANHAS) + 
				Constants.PATH_ASSOCIA_SOCIO_CAMPANHA + "/" + socio.getId() + "/" + socio.getTimeCoracao().getId();
		//Faz a associacao via API de campanhas
		restTemplate.put(url, String.class);
	}
	
}
