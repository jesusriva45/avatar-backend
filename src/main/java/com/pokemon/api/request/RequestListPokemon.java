package com.pokemon.api.request;

import lombok.Setter;

import lombok.Getter;

@Getter
@Setter
public class RequestListPokemon {
	
	private Integer offSet;
	private Integer limit;

}
