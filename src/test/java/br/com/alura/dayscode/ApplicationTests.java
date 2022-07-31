package br.com.alura.dayscode;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void shouldReturnTop250Films() {

		ResponseEntity<FilmeController.ListOfMovies> response =
				this.restTemplate.getForEntity("http://localhost:" + port + "/top250", FilmeController.ListOfMovies.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());

		assertEquals(250, response.getBody().items().size());
	}

	@Test
	void shouldInsertToFavorites() {

		String filmeId = "tt0068646";

		ResponseEntity<String> response =
				this.restTemplate.postForEntity("http://localhost:" + port + "/favorito/" + filmeId, null, String.class);

		assertTrue(response.getStatusCode().is2xxSuccessful());
		assertEquals(FilmeController.POST_SUCCESS, response.getBody());

	}
}
