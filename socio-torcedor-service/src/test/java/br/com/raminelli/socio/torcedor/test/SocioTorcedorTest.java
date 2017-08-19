package br.com.raminelli.socio.torcedor.test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.raminelli.ns.commons.model.Campanha;
import br.com.raminelli.ns.commons.model.SocioTorcedor;
import br.com.raminelli.ns.commons.model.Time;
import br.com.raminelli.socio.torcedor.SocioTorcedorApplication;

/**
 * Testes de cadastro de Socio Torcedor
 * A API CAMPANHAS DEVE ESTAR EM EXECUCAO PARA A MAIORIA DOS TESTES FUNCIONAR
 * 
 * @author raminelli
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(
  webEnvironment = WebEnvironment.DEFINED_PORT,
  classes = SocioTorcedorApplication.class)
public class SocioTorcedorTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private ObjectMapper mapper;
	
	private String URL = "http://localhost:8091/socios";
	private String URL_CAMPANHAS = "http://localhost:8090/campanhas";
	
	private Time timeCoracao;
	
	@Before
	public void init() throws JsonParseException, JsonMappingException, IOException {
		//busca um time do coracao associado a alguma campanha
		String response = restTemplate.getForObject(URL_CAMPANHAS, String.class);
		List<Campanha> campanhas =  mapper.readValue(response,
					mapper.getTypeFactory().constructCollectionType(List.class, Campanha.class));
		timeCoracao = campanhas.stream().filter(c -> c.getTimeCoracao() != null).findFirst().orElse(new Campanha()).getTimeCoracao();
	}
	
	@Test
	public void teste_pesquisa_por_email_nao_cadastrado() {		
		String response = restTemplate.getForObject(URL + "/email@ns.com", String.class);
		Assert.assertTrue(response.contains("Socio Torcedor não encontrado"));
	}
	
	@Test
	public void teste_pesquisa_por_email_ja_cadastrado_com_campanhas_associadas() {		
		SocioTorcedor socio = gerarSocioTorcedor(timeCoracao);
		socio = restTemplate.postForObject(URL, socio, SocioTorcedor.class);
		
		String response = restTemplate.getForObject(URL + "/" + socio.getEmail(), String.class);
		Assert.assertTrue(response.contains("Socio Torcedor já cadastrado"));
	}
	
	@Test
	public void teste_pesquisa_por_email_ja_cadastrado_sem_campanhas_associadas() throws JsonParseException, JsonMappingException, IOException {		
		SocioTorcedor socio = gerarSocioTorcedor(null);
		socio = restTemplate.postForObject(URL, socio, SocioTorcedor.class);
		
		String response = restTemplate.getForObject(URL + "/" + socio.getEmail(), String.class);
		List<Campanha> campanhas =  mapper.readValue(response,
				mapper.getTypeFactory().constructCollectionType(List.class, Campanha.class));
		Assert.assertTrue(!campanhas.isEmpty());
	}
	
	@Test
	public void teste_inserir_socio_torcedor() throws JsonParseException, JsonMappingException, IOException {		
		SocioTorcedor socio = gerarSocioTorcedor(timeCoracao);
		socio = restTemplate.postForObject(URL, socio, SocioTorcedor.class);
		
		String response = restTemplate.getForObject(URL + "/" + socio.getEmail(), String.class);
		Assert.assertTrue(response.contains("Socio Torcedor já cadastrado"));
	}
	
	@Test
	public void teste_lista_todos_socios() throws JsonParseException, JsonMappingException, IOException {		
		String response = restTemplate.getForObject(URL, String.class);
		List<SocioTorcedor> socios = mapper.readValue(response, 
		        mapper.getTypeFactory().constructCollectionType(List.class, SocioTorcedor.class));
		Assert.assertNotNull(socios);
		
	}
	
	private SocioTorcedor gerarSocioTorcedor(Time time) {
		SocioTorcedor socio = new SocioTorcedor();
		socio.setDataNascimento(new Date());
		socio.setEmail(new Date().getTime() + "teste@email.com");
		socio.setNome("Socio " + new Date().getTime());
		socio.setTimeCoracao(time);
		return socio;
	}
	
}
