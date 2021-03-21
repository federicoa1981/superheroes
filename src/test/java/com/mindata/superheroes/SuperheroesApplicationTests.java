package com.mindata.superheroes;

import com.mindata.superheroes.model.Superhero;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

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

		ResponseEntity<Iterable> entity = this.testRestTemplate.getForEntity("/superheroes", Iterable.class);

		assertThat(entity.getStatusCode(), is(HttpStatus.OK));

		Iterable<Superhero> superheros = entity.getBody();
		Long total = StreamSupport.stream(superheros.spliterator(), false).count();
		assertThat(total , is(3));
	}

	@Test
	public void create() throws Exception {
		Superhero superhero = new Superhero();
		superhero.setStrength(2000);
		superhero.setName("wonderWoman");

		HttpEntity<Superhero> request = new HttpEntity<>(superhero);
		ResponseEntity<Long> entity = this.testRestTemplate.postForEntity("/superheroes", request, Long.class);

		assertThat(entity.getStatusCode(), is(HttpStatus.CREATED));
	}

	@Test
	public void delete() throws Exception {
		Map<String, String > params = new HashMap<>();
		params.put("id", "1");
		this.testRestTemplate.delete("/superheroes/{id}", params);
	}


}
