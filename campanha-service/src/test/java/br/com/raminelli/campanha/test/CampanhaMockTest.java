package br.com.raminelli.campanha.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.raminelli.campanha.repository.CampanhaRepository;
import br.com.raminelli.ns.commons.model.Campanha;
import br.com.raminelli.ns.commons.model.Time;
import br.com.raminelli.ns.commons.utils.DateUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CampanhaMockTest {

	@MockBean
	private CampanhaRepository campanhaRepository;
	
	@Test
	public void teste_inserir_nova_campanha() {
		Campanha campanha = gerarCampanha();		
		given(this.campanhaRepository.insert(campanha)).willReturn(campanha);		
		Campanha retorno = campanhaRepository.insert(campanha);
		assertThat(campanha.getNome()).isEqualTo(retorno.getNome());
	}
	
	@Test
	public void teste_atualizar_campanha() {
		Campanha campanha = gerarCampanha();		
		given(this.campanhaRepository.save(campanha)).willReturn(campanha);		
		Campanha retorno = campanhaRepository.save(campanha);
		assertThat(campanha.getNome()).isEqualTo(retorno.getNome());
	}
	
	@Test
	public void teste_recuperar_lista_campanhas_ativas() {
		List<Campanha> campanhas = new ArrayList<>();
		campanhas.add(gerarCampanha());
		campanhas.add(gerarCampanha());
		
		Date data = new Date();
		given(this.campanhaRepository.findByDataTerminoGreaterThanEqual(data)).willReturn(campanhas);		
		List<Campanha> retorno = campanhaRepository.findByDataTerminoGreaterThanEqual(data);
		assertThat(retorno.size()).isNotZero();
	}
	
	private Campanha gerarCampanha() {
		Campanha campanha = new Campanha();
		campanha.setDataInicio(DateUtils.parse("01/01/2017"));
		campanha.setDataTermino(new Date());
		campanha.setNome("Campanha teste " + new Date().getTime());
		campanha.setTimeCoracao(new Time("Time Teste"));
		return campanha;
	}
}
