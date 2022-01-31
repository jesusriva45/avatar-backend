package com.pokemon.api.util;

public class Util {

	public static final String URL_IMG_POKEMON_PNG = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";
	public static final String EXT_IMG_PNG = ".png";

	public static final String URL_IMG_POKEMON_SVG = "https://unpkg.com/pokeapi-sprites@2.0.2/sprites/pokemon/other/dream-world/";
	public static final String EXT_IMG_SVG = ".svg";

	public static final String EXT_IMG_JPG = ".jpg";

	public static final String URL_POKEMON_SPECIES(int offset, int limit) {
		return "https://pokeapi.co/api/v2/pokemon-species?offset=" + offset + "&limit=" + limit;
	}

}
