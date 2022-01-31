package com.pokemon.api.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

@Service
public class RestTemplateServiceImpl implements IRestTemplateService {

	@Override
	public ResponseEntity<?> restTemplate(String url, Object requestBody, HttpMethod method) throws Exception {

		ResponseEntity<?> responsePokemonEntity = null;

		try {

			TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
				@Override
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			};

			SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
					.loadTrustMaterial(null, acceptingTrustStrategy).build();

			SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

			CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();

			HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

			requestFactory.setConnectTimeout(20000);
			requestFactory.setConnectionRequestTimeout(20000);
			requestFactory.setReadTimeout(30000);
			requestFactory.setHttpClient(httpClient);
			
			RestTemplate restTemplate = new RestTemplate(requestFactory);

			HttpEntity<Object> entity = new HttpEntity<Object>(requestBody, headers());

			responsePokemonEntity = restTemplate.exchange(url, method, entity, Object.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
