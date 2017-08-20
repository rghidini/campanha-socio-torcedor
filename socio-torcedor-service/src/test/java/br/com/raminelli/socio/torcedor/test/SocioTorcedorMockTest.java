package br.com.raminelli.socio.torcedor.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.raminelli.ns.commons.model.SocioTorcedor;
import br.com.raminelli.ns.commons.model.Time;
import br.com.raminelli.socio.torcedor.repository.SocioTorcedorRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SocioTorcedorMockTest {

	@MockBean
	private SocioTorcedorRepository socioTorcedorRepository;
	
	@Test
	public void teste_inserir_novo_socio() {
		SocioTorcedor socio = gerarSocioTorcedor();		
		given(this.socioTorcedorRepository.insert(socio)).willReturn(socio);		
		SocioTorcedor retorno = socioTorcedorRepository.insert(socio);
		assertThat(socio.getNome()).isEqualTo(retorno.getNome());
	}
	
	@Test
	public void teste_buscar_socio_por_email() {
		SocioTorcedor socio = gerarSocioTorcedor();		
		given(this.socioTorcedorRepository.findByEmail(socio.getEmail())).willReturn(socio);		
		SocioTorcedor retorno = socioTorcedorRepository.findByEmail(socio.getEmail());
		assertThat(socio.getNome()).isEqualTo(retorno.getNome());
	}
	
	private SocioTorcedor gerarSocioTorcedor() {
		SocioTorcedor socio = new SocioTorcedor();
		socio.setDataNascimento(new Date());
		socio.setEmail(new Date().getTime() + "teste@email.com");
		socio.setNome("Socio " + new Date().getTime());
		socio.setTimeCoracao(new Time("Time Teste"));
		return socio;
	}
}
