package com.apisw.repository;

import com.apisw.model.Planet;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Renan Siqueira on 19/05/2019.
 * Repository Planet Class
 */
public interface PlanetsRepository extends MongoRepository<Planet, String> {
    Planet findBy_id(ObjectId _id);
    Planet findPlanetByNameEquals(String name);
}