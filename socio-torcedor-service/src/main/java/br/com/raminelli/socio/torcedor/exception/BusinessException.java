package br.com.raminelli.socio.torcedor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.ACCEPTED)
public class BusinessException extends RuntimeException {

	public BusinessException (String msg) {
		super(msg);
	}
	
}
