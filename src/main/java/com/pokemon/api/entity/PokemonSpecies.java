package com.pokemon.api.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PokemonSpecies {

	private Integer total;
	private String nextList;
	private String previousList;
	private List<Pokemon> listPokemons;

}
