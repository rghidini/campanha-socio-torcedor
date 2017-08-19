package br.com.raminelli.campanha.test;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
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

import br.com.raminelli.campanha.CampanhaApplication;
import br.com.raminelli.ns.commons.model.Time;

@RunWith(SpringRunner.class)
@SpringBootTest(
  webEnvironment = WebEnvironment.DEFINED_PORT,
  classes = CampanhaApplication.class)
public class TimeCoracaoTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private ObjectMapper mapper;
	
	private String URL = "http://localhost:8090/times";
	
	@Test
	public void teste_lista_todos_times() throws JsonParseException, JsonMappingException, IOException {		
		String response = restTemplate.getForObject(URL, String.class);
		List<Time> times = mapper.readValue(response, 
		        mapper.getTypeFactory().constructCollectionType(List.class, Time.class));
		Assert.assertNotNull(times);		
	}
	
	@Test
	public void teste_recupera_time_por_id() throws JsonParseException, JsonMappingException, IOException {
		Time timeBusca = restTemplate.getForObject(URL + "/1", Time.class);
		Assert.assertNull(timeBusca);
	}
	
}
