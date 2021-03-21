package com.mindata.superheroes;

import com.mindata.superheroes.model.Superhero;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.StreamSupport;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SuperheroesApplicationTests {

	public static final String SUPERHEROES_RESOURCE_URL = "/superheroes";
	@Autowired
	private TestRestTemplate testRestTemplate;




	protected Superhero createWonderWoman(){
		Superhero superhero = new Superhero();
		superhero.setStrength(2000);
		superhero.setSpeed(500);
		superhero.setName("wonderWoman");
		return superhero;
	}


	protected Superhero createSpiderman(){
		Superhero superhero = new Superhero();
		superhero.setStrength(100);
		superhero.setSpeed(1000);
		superhero.setName("Spiderman");
		return superhero;
	}

	@Test
	public void createUpdateDelete() {
		Superhero superhero = createWonderWoman();

		HttpEntity<Superhero> request = new HttpEntity<>(superhero);
		// create and get
		ResponseEntity<Long> entity = this.testRestTemplate.postForEntity(SUPERHEROES_RESOURCE_URL, request, Long.class);
		assertThat(entity.getStatusCode(), is(HttpStatus.CREATED));

		ResponseEntity<Superhero> entityFound = this.testRestTemplate.getForEntity(String.format( "%s/%s",SUPERHEROES_RESOURCE_URL, entity.getBody()), Superhero.class );
		assertThat(entityFound.getStatusCode(), is(HttpStatus.OK));
		Superhero superheroFound = entityFound.getBody();
		assertThat(superheroFound.getId(), is(entity.getBody()));
		assertThat(superheroFound.getStrength(), is(superhero.getStrength()));
		assertThat(superheroFound.getSpeed(), is(superhero.getSpeed()));
		assertThat(superheroFound.getName(), is(superhero.getName()));

		// update and check
		Superhero updateSuperhero = superheroFound;
		updateSuperhero.setSpeed(800);
		updateSuperhero.setStrength(400);
		this.testRestTemplate.put(String.format( "%s/%s",SUPERHEROES_RESOURCE_URL, updateSuperhero.getId()), updateSuperhero);

		ResponseEntity<Superhero> entityUpdated = this.testRestTemplate.getForEntity(String.format( "%s/{id}",SUPERHEROES_RESOURCE_URL), Superhero.class, entity.getBody());
		assertThat(entityUpdated.getStatusCode(), is(HttpStatus.OK));
		Superhero superheroUpdatedFound = entityUpdated.getBody();
		assertThat(superheroUpdatedFound.getId(), is(updateSuperhero.getId()));
		assertThat(superheroUpdatedFound.getStrength(), is(400));
		assertThat(superheroUpdatedFound.getSpeed(), is(800));
		assertThat(superheroUpdatedFound.getName(), is(updateSuperhero.getName()));

		//delete and get
		Map<String, String > params = new HashMap<>();
		params.put("id", String.valueOf(superheroUpdatedFound.getId()));
		this.testRestTemplate.delete(String.format("%s/{id}",SUPERHEROES_RESOURCE_URL),  params);

		ResponseEntity<Superhero> entityDeleted = this.testRestTemplate.getForEntity(String.format( "%s/{id}",SUPERHEROES_RESOURCE_URL), Superhero.class, superheroUpdatedFound.getId());
		assertThat(entityDeleted.getStatusCode(), is(HttpStatus.NOT_FOUND));
	}


	@Test
	public void searchAll()  {
		HttpEntity<Superhero> request = new HttpEntity<>(createWonderWoman());
		// create first superhero
		ResponseEntity<Long> entity = this.testRestTemplate.postForEntity(SUPERHEROES_RESOURCE_URL, request, Long.class);
		assertThat(entity.getStatusCode(), is(HttpStatus.CREATED));

		request = new HttpEntity<>(createSpiderman());
		// create second superhero
		ResponseEntity<Long> entity2 = this.testRestTemplate.postForEntity(SUPERHEROES_RESOURCE_URL, request, Long.class);
		assertThat(entity2.getStatusCode(), is(HttpStatus.CREATED));

		// searchAll
		ResponseEntity<Iterable> entities = this.testRestTemplate.getForEntity(SUPERHEROES_RESOURCE_URL, Iterable.class);
		assertThat(entities.getStatusCode(), is(HttpStatus.OK));
		Iterable<Superhero> superheros = entities.getBody();
		Long total = StreamSupport.stream(superheros.spliterator(), false).count();
		assertThat(total , is(2L));
	}


}
