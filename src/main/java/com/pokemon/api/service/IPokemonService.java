package com.pokemon.api.service;

import com.pokemon.api.entity.PokemonSpecies;

public interface IPokemonService {

	public abstract PokemonSpecies pokemonSpecies(Integer offset, Integer limit);

}
