package br.com.alura.dayscode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class FilmeController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/top250")
    public ListOfMovies getTop250Filmes() {

        ResponseEntity<ListOfMovies> response =
                this.restTemplate.getForEntity("https://imdb-api.com/en/API/Top250Movies/k_puevtnbr", ListOfMovies.class);

        return response.getBody();
    }

    record Movie(String title, String image, String year, String imDbRating){}
    record ListOfMovies(List<Movie> items){}

}
