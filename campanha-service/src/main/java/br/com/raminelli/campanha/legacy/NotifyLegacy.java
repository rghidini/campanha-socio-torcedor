package br.com.raminelli.campanha.legacy;

import java.util.Set;

import org.springframework.stereotype.Component;

import br.com.raminelli.campanha.utils.PropertiesLegacyLoader;
import br.com.raminelli.ns.commons.model.Campanha;

/**
 * Simula a notificacao para sistemas legados quando houver alteracao de campanha
 * 
 * @author rraminelli
 *
 */

@Component
public class NotifyLegacy {

	public void notifyUpdateCampanha(final Campanha campanha) {
		
		final Set<Object> keysLegacy = PropertiesLegacyLoader.getInstance().getListKeys();
		keysLegacy.forEach((legacy) -> notifyLegacy(campanha, legacy.toString()));
		
	}
	
	private void notifyLegacy(final Campanha campanha, final String keyLegacy) {
		
		String message = String.format("Simula notificação de alteração da campanha %s para o legado %s", 
				campanha.getNome(), PropertiesLegacyLoader.getInstance().getValue(keyLegacy));
		
		System.out.println(message);
	}
	
}
