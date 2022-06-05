package com.residencia.commerce.service.specification;

import com.residencia.commerce.dto.ApiCepDTO;

public interface ICepApiService {
	ApiCepDTO consultar(String cep);
}
