package com.pokemon.api.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public interface IRestTemplateService {
	
	public abstract ResponseEntity<?> restTemplate(String url, Object Json, HttpMethod method) throws Exception;

	

	public abstract HttpHeaders headers() throws Exception;

}
