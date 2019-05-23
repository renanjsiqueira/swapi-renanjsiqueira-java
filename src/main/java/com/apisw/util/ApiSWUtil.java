package com.apisw.util;

import com.swapi.models.Planet;
import com.swapi.models.SWModelList;
import com.swapi.sw.StarWars;
import com.swapi.sw.StarWarsApi;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


/**
 * Created by Renan Siqueira on 22/05/2019.
 * ApiSWUtil Class for api swapi abstract
 */

public abstract  class ApiSWUtil {

    private static final StarWars api = StarWarsApi.getApi();


    public static List<Planet> getAllPlanets() throws IOException {
        try {
            Call<SWModelList<Planet>> planetsApi = api.getAllPlanets(null);
            Response<SWModelList<Planet>> response = planetsApi.execute();
            if (response.isSuccessful()) {
                SWModelList<Planet> data = response.body();
                return data.results;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Planet getPlanetById(Integer id) throws IOException {
        try {

            Call<com.swapi.models.Planet> planetSwApi = api.getPlanet(id);
            Response<com.swapi.models.Planet> response = planetSwApi.execute();
            if (response.isSuccessful()) {
                final Planet planetSWApi = response.body();
                return planetSWApi;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Planet getPlanetByName(String nome) throws IOException {
        try {



            List<Planet> planetSwApi = getAllPlanets();

            Optional<Planet> planet = planetSwApi.stream()
                    .filter((Planet p) -> p.name.equals(nome)).findFirst();
                if(planet.isPresent())
                 return planet.get();
        }
            catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
