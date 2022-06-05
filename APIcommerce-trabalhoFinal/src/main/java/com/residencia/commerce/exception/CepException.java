package com.residencia.commerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.residencia.commerce.dto.ApiCepDTO;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CepException extends RuntimeException{

	public CepException(String msg) {
		super(msg);
	}
}