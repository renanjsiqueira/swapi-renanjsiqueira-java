package com.apisw.resource;

import com.apisw.model.Planet;
import com.apisw.repository.PlanetsRepository;
import com.apisw.util.ApiSWUtil;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
/**
 * Created by Renan Siqueira on 21/05/2019.
 * Api Interface Class
 */
@RestController
@RequestMapping("/api/")
public class ApiSWResource {



	@Autowired
	private PlanetsRepository planets;

	@GetMapping("/planets/")
	public List<Planet> getAllPlanets() {
		return planets.findAll();
	}
    // listar planetas da api do star wars
    @GetMapping("/swapi/planets")
    public ResponseEntity<List> getAllPlanetsSwAPI() throws IOException {
        try {
           List<com.swapi.models.Planet> planetsApi = ApiSWUtil.getAllPlanets();
           if(planetsApi != null)
           {
               return  ResponseEntity.ok( planetsApi);
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
            return ResponseEntity.badRequest().build();

    }

// Buscar por nome no banco de dados
    @GetMapping("/planets/name/{nome}")
    public ResponseEntity getPlanetByName(@PathVariable String nome) {

        Planet planet = planets.findPlanetByNameEquals(nome);


        if (planet == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(planet);
    }
	//  Buscar por ID no banco de dados
    @GetMapping(value = "/planets/{id}")
	public ResponseEntity<Planet> getPlanetByID(@PathVariable("id") ObjectId id) {

		Planet planet = planets.findBy_id(id);

		if (planet == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(planet);
	}

    @PutMapping(value = "/planets/{id}")
    public ResponseEntity<Planet> modifyPlanetById(@PathVariable("id") ObjectId id, @Valid @RequestBody Planet planet) {
        planet.set_id(id);
        planets.save(planet);
        return ResponseEntity.ok(planet);
    }

    @PostMapping(value = "/planets/")
    public ResponseEntity<Planet> createPlanet(@Valid @RequestBody Planet planet) {
        try {
        //nome,se não passado no json é uma requisição invalida
        final String namePlanet = planet.getName();
        if(namePlanet == null)
        {
            return ResponseEntity.unprocessableEntity().build();
        }
        Planet planetFind = planets.findPlanetByNameEquals(namePlanet);
        if (planetFind == null) {
            final com.swapi.models.Planet planetApi;

                planetApi = ApiSWUtil.getPlanetByName(namePlanet);

            if (planetApi != null) {
                planet.setQtdFilms(planetApi.filmsUrls.size());
                planets.save(planet);
                return ResponseEntity.ok(planet);
            } else {
                //planeta não encontrado na api da swapi, salva sem dados de films
                planet.setQtdFilms(0);
            }
            planet.set_id(ObjectId.get());
            planets.save(planet);
            return ResponseEntity.ok(planet);
        }
        else
        {
            //se ja existe um planeta com o mesmo nome apenas atualiza as informações.
            planetFind.setClimate(planet.getClimate());
            planetFind.setTerrain(planet.getTerrain());
            planets.save(planetFind);
            return ResponseEntity.ok(planetFind);
        }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("/planets/{id}")
    public ResponseEntity<Void> remove(@PathVariable("id") ObjectId id) {
        Planet planet = planets.findBy_id(id);

        if (planet == null) {
            return ResponseEntity.notFound().build();
        }

        planets.delete(planet);

        return ResponseEntity.noContent().build();
    }



}











