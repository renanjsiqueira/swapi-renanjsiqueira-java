package com.apisw.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

/**
 * Created by Renan Siqueira on 19/05/2019.
 * Model Planet Class
 */
public class Planet {

	@Id
	private ObjectId _id;
	
	private String name;

	public String climate;

	public String terrain;

	public Integer qtdFilms;

	public Planet(){};

    public Planet(ObjectId _id, String name, String climate, String terrain, Integer qtdFilms) {
        this._id = _id;
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
        this.qtdFilms = qtdFilms;
    }

    public String get_id() { return _id.toHexString(); }

    public void set_id(ObjectId _id) { this._id = _id; }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	public String getTerrain() {
		return terrain;
	}

	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}

	public Integer getQtdFilms() {
		return qtdFilms;
	}

	public void setQtdFilms(Integer qtdFilms) {
		this.qtdFilms = qtdFilms;
	}







	
}
