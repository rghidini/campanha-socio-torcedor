package br.com.raminelli.socio.torcedor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.raminelli.ns.commons.model.Campanha;
import br.com.raminelli.ns.commons.model.SocioTorcedor;
import br.com.raminelli.socio.torcedor.exception.BusinessException;
import br.com.raminelli.socio.torcedor.repository.SocioTorcedorRepository;
import br.com.raminelli.socio.torcedor.service.CampanhaService;

@RestController
public class SocioTorcedorController {
	
	private static final String MSG_TORCEDOR_NAO_CADASTRADO = "Socio Torcedor não encontrado";
	private static final String MSG_TORCEDOR_JA_CADASTRADO = "Socio Torcedor já cadastrado";
	
	@Autowired
	private SocioTorcedorRepository socioTorcedorRepository;
	
	@Autowired
	private CampanhaService campanhaService;
	
	@GetMapping("/socios/{email:.+}")
	public List<Campanha> findByEmail(@PathVariable String email) {
		
		SocioTorcedor socio = socioTorcedorRepository.findByEmail(email);
		
		if (socio == null) {
			//Cadastrado nao encontrado
			throw new BusinessException(MSG_TORCEDOR_NAO_CADASTRADO);
		} 
		
		//Busca as campanhas do socio na API campanhas
		final List<Campanha> campanhasSocioTorcedor = campanhaService.findBySocioTorcedor(socio); 
		
		if (!campanhasSocioTorcedor.isEmpty()) {
			throw new BusinessException(MSG_TORCEDOR_JA_CADASTRADO);
		} else {
			final List<Campanha> campanhasNovas = campanhaService.findAll(); //Busca as novas campanhas na API campanhas
			return campanhasNovas; //Cadastro ja efetuado mas sem campanhas, deve retornar a lista de novas campanhas
		}
	}	
	
	@PostMapping("/socios")
	public SocioTorcedor insert(@RequestBody SocioTorcedor socio) {
		SocioTorcedor socioCadastrado = socioTorcedorRepository.findByEmail(socio.getEmail());
		
		if(socioCadastrado == null) {
			socioCadastrado = socioTorcedorRepository.insert(socio);
		} 
		
		try {
			//Chama a API campanha e faz a associacao com as campanhas
			campanhaService.addSocioTorcedorCampanha(socioCadastrado);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return socioCadastrado;
	}
	
	@GetMapping("/socios")
	public List<SocioTorcedor> listAll() {
		return socioTorcedorRepository.findAll();
	}
	
}
