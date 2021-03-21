package com.mindata.superheroes;

import com.mindata.superheroes.model.Superhero;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SuperheroesApplicationTests {

	@Autowired
	private TestRestTemplate testRestTemplate;


	@Test
	void contextLoads() {
	}

	@Test
	public void get() throws Exception {
		ResponseEntity<Superhero> entity = this.testRestTemplate.getForEntity("/superheroes/{id}", Superhero.class, 1L);

		assertThat(entity.getStatusCode(), is(HttpStatus.OK));
		Superhero superhero = entity.getBody();
		assertThat(superhero, notNullValue());
		assertThat(superhero.getId(), is(1L));
		assertThat(superhero.getName(), is("SpiderMan"));
		assertThat(superhero.getSpeed(), is(20));
		assertThat(superhero.getStrength(), is(23));

	}

	@Test
	public void searchAll() throws Exception {

		ResponseEntity<List> entity = this.testRestTemplate.getForEntity("/superheroes", List.class);

		assertThat(entity.getStatusCode(), is(HttpStatus.OK));

		List<Superhero> superheros = entity.getBody();
		assertThat(superheros, hasSize(3));
	}


}
