package br.com.raminelli.campanha.test;

import java.io.IOException;
import java.util.Date;
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
import br.com.raminelli.ns.commons.model.Campanha;
import br.com.raminelli.ns.commons.model.Time;
import br.com.raminelli.ns.commons.utils.DateUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, classes = CampanhaApplication.class)
public class CampanhaTest {

	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private ObjectMapper mapper;

	private String URL = "http://localhost:8090/campanhas";
	private String URL_TIMES = "http://localhost:8090/times";

	@Test
	public void teste_inserir_campanha() {
		Time time = new Time("TimeCoracaoTest " + new Date().getTime());
		time = restTemplate.postForObject(URL_TIMES, time, Time.class);

		Campanha campanha = gerarCampanha(time);
		Campanha response = restTemplate.postForObject(URL, campanha, Campanha.class);
		Assert.assertTrue(campanha.getNome().equals(response.getNome()));
	}

	@Test
	public void teste_inserir_campanha_verificar_vigencia() {
		Time time = new Time("TimeCoracaoTest " + new Date().getTime());
		time = restTemplate.postForObject(URL_TIMES, time, Time.class);

		Campanha campanha1 = gerarCampanha(time);
		campanha1.setDataTermino(DateUtils.parse("03/10/2017"));
		Campanha campanha2 = gerarCampanha(time);
		campanha2.setDataTermino(DateUtils.parse("02/10/2017"));
		Campanha campanha3 = gerarCampanha(time);
		campanha3.setDataTermino(DateUtils.parse("03/10/2017"));

		// Insere as campanhas
		campanha1 = restTemplate.postForObject(URL, campanha1, Campanha.class);
		campanha2 = restTemplate.postForObject(URL, campanha2, Campanha.class);
		campanha3 = restTemplate.postForObject(URL, campanha3, Campanha.class);

		//Busca as campanhas
		campanha1 = restTemplate.getForObject(URL + "/" + campanha1.getId(),Campanha.class);
		campanha2 = restTemplate.getForObject(URL + "/" + campanha2.getId(),Campanha.class);
		campanha3 = restTemplate.getForObject(URL + "/" + campanha3.getId(),Campanha.class);

		Assert.assertTrue(campanha1.getDataTermino().after(DateUtils.parse("03/10/2017")));
		Assert.assertTrue(campanha2.getDataTermino().after(DateUtils.parse("03/10/2017")));
		Assert.assertTrue(campanha3.getDataTermino().equals(DateUtils.parse("03/10/2017")));

	}

	@Test
	public void teste_lista_todas_campanhas() throws JsonParseException, JsonMappingException, IOException {
		Time time = new Time("TimeCoracaoTest " + new Date().getTime());
		time = restTemplate.postForObject(URL_TIMES, time, Time.class);

		Campanha campanha = gerarCampanha(time);
		Campanha campanhaResp = restTemplate.postForObject(URL, campanha, Campanha.class);
		Assert.assertTrue(campanha.getNome().equals(campanhaResp.getNome()));

		String response = restTemplate.getForObject(URL, String.class);
		List<Campanha> campanhas =  mapper.readValue(response,
					mapper.getTypeFactory().constructCollectionType(List.class, Campanha.class));
		
		Assert.assertTrue(campanhas.size() > 0);

	}

	@Test
	public void teste_recupera_campanha_por_id() {
		Time time = new Time("TimeCoracaoTest " + new Date().getTime());
		time = restTemplate.postForObject(URL_TIMES, time, Time.class);

		Campanha campanha = gerarCampanha(time);
		Campanha response = restTemplate.postForObject(URL, campanha, Campanha.class);
		Assert.assertTrue(campanha.getNome().equals(response.getNome()));

		Campanha campanhaBusca = restTemplate.getForObject(URL + "/" + response.getId(), Campanha.class);
		Assert.assertTrue(campanhaBusca.getNome().equals(response.getNome()));
		Assert.assertTrue(campanhaBusca.getNome().equals(campanha.getNome()));
	}

	@Test
	public void teste_atualizar_campanha() {
		Time time = new Time("TimeCoracaoTest " + new Date().getTime());
		time = restTemplate.postForObject(URL_TIMES, time, Time.class);

		// Insere a campanha
		Campanha campanha = gerarCampanha(time);
		Campanha response = restTemplate.postForObject(URL, campanha, Campanha.class);
		Assert.assertTrue(campanha.getNome().equals(response.getNome()));

		// Valida a existencia da campanha
		Campanha campanhaBusca = restTemplate.getForObject(URL + "/" + response.getId(), Campanha.class);
		Assert.assertTrue(campanhaBusca.getNome().equals(response.getNome()));
		Assert.assertTrue(campanhaBusca.getNome().equals(campanha.getNome()));

		// Atualiza a campanha
		campanhaBusca.setNome("novo nome");
		restTemplate.put(URL + "/" + campanhaBusca.getId(), campanhaBusca);

		// Valida a alteracao da campanha
		Campanha campanhaBuscaAtualizada = restTemplate.getForObject(URL + "/" + response.getId(), Campanha.class);
		Assert.assertTrue(campanhaBuscaAtualizada.getNome().equals(campanhaBusca.getNome()));

	}

	@Test
	public void teste_excluir_campanha_por_id() {
		Time time = new Time("TimeCoracaoTest " + new Date().getTime());
		time = restTemplate.postForObject(URL_TIMES, time, Time.class);

		Campanha campanha = gerarCampanha(time);
		Campanha response = restTemplate.postForObject(URL, campanha, Campanha.class);
		Assert.assertTrue(campanha.getNome().equals(response.getNome()));

		Campanha campanhaBusca = restTemplate.getForObject(URL + "/" + response.getId(), Campanha.class);
		Assert.assertTrue(campanhaBusca.getNome().equals(response.getNome()));
		Assert.assertTrue(campanhaBusca.getNome().equals(campanha.getNome()));

		restTemplate.delete(URL + "/" + response.getId());

		campanhaBusca = restTemplate.getForObject(URL + "/" + response.getId(), Campanha.class);
		Assert.assertNull(campanhaBusca);
	}

	private Campanha gerarCampanha(Time time) {
		Campanha campanha = new Campanha();
		campanha.setDataInicio(DateUtils.parse("01/01/2017"));
		campanha.setDataTermino(new Date());
		campanha.setNome("Campanha teste " + new Date().getTime());
		campanha.setTimeCoracao(time);
		return campanha;
	}

}
