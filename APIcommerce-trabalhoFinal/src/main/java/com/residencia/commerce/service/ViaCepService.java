package com.residencia.commerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.residencia.commerce.dto.ApiCepDTO;
import com.residencia.commerce.exception.CepException;
import com.residencia.commerce.service.specification.ICepApiService;

@Service
public class ViaCepService implements ICepApiService {

	private final String URL_VIA_CEP = "http://viacep.com.br/ws/";
	private final String JSON_FORMAT = "/json";

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ApiCepDTO consultar(String cep) {

		try {
			String url = URL_VIA_CEP + cep + JSON_FORMAT;
			
			return restTemplate.getForObject(url, ApiCepDTO.class);
			
		} catch (Exception e) {
			
			if(e.getMessage().contains("400 Bad Request")) {
				throw new CepException("Cep inexistente");
			}
			
			throw e;
		}
	}

}
