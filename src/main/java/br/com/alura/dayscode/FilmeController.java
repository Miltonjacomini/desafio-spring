package br.com.alura.dayscode;

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
    public String getTop250Filmes() {

        ResponseEntity<String> response =
                this.restTemplate.getForEntity("https://imdb-api.com/en/API/Top250Movies/k_puevtnbr", String.class);

        return response.getBody();
    }

}
