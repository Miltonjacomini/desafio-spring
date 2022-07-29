package br.com.alura.dayscode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ImdbApiClient {

    @Value("${imdb.apikey}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    public FilmeController.ListOfMovies getBody() {
        ResponseEntity<FilmeController.ListOfMovies> response =
                this.restTemplate.getForEntity("https://imdb-api.com/en/API/Top250Movies/"+apiKey, FilmeController.ListOfMovies.class);

        return response.getBody();
    }

}
