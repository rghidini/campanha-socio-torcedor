package br.com.raminelli.campanha.loader;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.com.raminelli.campanha.repository.CampanhaRepository;
import br.com.raminelli.campanha.repository.TimeRepository;
import br.com.raminelli.ns.commons.model.Campanha;
import br.com.raminelli.ns.commons.model.Time;

@Component
public class AppLoader implements ApplicationRunner {

	@Autowired
	private TimeRepository timeRepository;
	
	@Autowired
	private CampanhaRepository campanhaRepository;
	
	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		//timeRepository.deleteAll();
		//campanhaRepository.deleteAll();
		
		if(timeRepository.findAll().isEmpty()) {
			List<Time> times = new ArrayList<>();
			times.add(new Time("Gremio"));
			times.add(new Time("Santos"));
			times.add(new Time("Palmeiras"));
			times.add(new Time("Sao Paulo"));
			times.add(new Time("Corinthians"));
			times.add(new Time("Sport Recife"));
			times.add(new Time("Atletico-PR"));
			times.add(new Time("Flamengo"));
			times.add(new Time("Botafogo"));
			times.add(new Time("Cruzeiro"));
			times.add(new Time("Fluminense"));
			times.add(new Time("Coritiba"));
			times = timeRepository.insert(times);
			
//			Campanha c = new Campanha();
//			c.setDataInicio(new Date());
//			c.setDataTermino(new Date());
//			c.setNome("teste inicial");
//			c.setTimeCoracao(times.get(0));
//			campanhaRepository.insert(c);
		}
		
	}

}
