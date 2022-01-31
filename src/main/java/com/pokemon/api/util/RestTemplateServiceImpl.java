package com.pokemon.api.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateServiceImpl implements IRestTemplateService {

	@Override
	public ResponseEntity<?> restTemplate(String url, Object requestBody, HttpMethod method) throws Exception {

		ResponseEntity<?> responsePokemonEntity = null;

		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<Object> entity = new HttpEntity<Object>(requestBody, headers());

		responsePokemonEntity = restTemplate.exchange(url, method, entity, Object.class);

		return responsePokemonEntity;
	}

	@Override
	public HttpHeaders headers() throws Exception {

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);

		// headers.set("Content-Type", "application/json");

		return headers;
	}

}
