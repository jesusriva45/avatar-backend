package com.pokemon.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pokemon.api.entity.Pokemon;
import com.pokemon.api.entity.PokemonSpecies;
import com.pokemon.api.util.IRestTemplateService;
import com.pokemon.api.util.Util;

@Service
public class PokemonServiceImpl implements IPokemonService {

	@Autowired
	private IRestTemplateService restTemplateService;

	@Override
	public PokemonSpecies pokemonSpecies(Integer offset, Integer limit) {

		ResponseEntity<?> responseEntity = null;
		PokemonSpecies responseBody = new PokemonSpecies();
		ObjectMapper objectMapper = new ObjectMapper();

		List<Pokemon> pokemons = new ArrayList<Pokemon>();

		try {

			responseEntity = restTemplateService.restTemplate(Util.URL_POKEMON_SPECIES(offset, limit), null,
					HttpMethod.GET);

			String responseJson = objectMapper.writeValueAsString(responseEntity.getBody());

			JsonObject jsonResponse = new JsonParser().parse(responseJson).getAsJsonObject();

			if (!jsonResponse.get("count").isJsonNull()) {
				responseBody.setTotal(jsonResponse.get("count").getAsInt());
			}

			if (!jsonResponse.get("next").isJsonNull()) {
				responseBody.setNextList(jsonResponse.get("next").getAsString());
			}

			if (!jsonResponse.get("previous").isJsonNull()) {
				responseBody.setPreviousList(jsonResponse.get("previous").getAsString());
			}

			JsonArray jsonArray = jsonResponse.getAsJsonArray("results");

			for (JsonElement jsonElement : jsonArray) {

				Integer id = obtenerIdPokemon(jsonElement.getAsJsonObject().get("url").getAsString());
				String name = jsonElement.getAsJsonObject().get("name").getAsString();
				String url = jsonElement.getAsJsonObject().get("url").getAsString();
				String imgUrl = getUrlImage(id, Util.EXT_IMG_PNG);

				pokemons.add(new Pokemon(id, name, url, imgUrl));

			}

			responseBody.setListPokemons(pokemons);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseBody;
	}

	private int obtenerIdPokemon(String url) {
		String idStr = "";
		int idPokemon = 0;

		StringBuilder strb = new StringBuilder(url);
		url = strb.reverse().toString();

		StringBuilder idStrb = new StringBuilder(url.split("/")[1]);

		idStr = idStrb.reverse().toString();

		idPokemon = Integer.parseInt(idStr);

		return idPokemon;

	}

	private String getUrlImage(int idPokemon, String extensionImg) {
		String urlImg = "";
		urlImg = Util.URL_IMG_POKEMON_PNG + idPokemon + extensionImg;
		return urlImg;
	}

}
