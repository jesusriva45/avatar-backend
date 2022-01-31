package com.pokemon.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;

import com.pokemon.api.entity.PokemonSpecies;
import com.pokemon.api.service.IPokemonService;


@CrossOrigin(origins = { "http://localhost:4200", "https://avatar-poke-api.web.app" })
@RestController
@RequestMapping(value = "/rest")
public class PokemonController {

	@Autowired
	private IPokemonService pokemonService;

	@GetMapping(value = "/pokemon-species/{offSet}/{limit}")
	// @ExceptionHandler
	public PokemonSpecies pokemonController(@PathVariable Integer offSet, @PathVariable Integer limit) {

		PokemonSpecies responseBody = new PokemonSpecies();

		responseBody = pokemonService.pokemonSpecies(offSet, limit);

		return responseBody;
	}

}
