package com.pokemon.api.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pokemon {

	private Integer id;
	private String name;
	private String url;
	private String imgUrl;

	public Pokemon(Integer id, String name, String url,String imgUrl) {
		this.id = id;
		this.name = name;
		this.url = url;
		this.imgUrl = imgUrl;
	}

	public Pokemon() {
		// TODO Auto-generated constructor stub
	}

}
