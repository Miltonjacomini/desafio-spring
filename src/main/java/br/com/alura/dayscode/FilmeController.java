package br.com.alura.dayscode;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilmeController {

    @Autowired
    private ImdbApiClient imdbApiClient;

    @GetMapping("/top250")
    public ListOfMovies getTop250Filmes() throws FileNotFoundException {

        ListOfMovies response = this.imdbApiClient.getBody();

        PrintWriter writer = new PrintWriter("src/main/resources/content.html");
        new HTMLGenerator(writer).generate(response);
        writer.close();

        return response;
    }

    record Movie(String title, String image, String year, String imDbRating){}
    record ListOfMovies(List<Movie> items){}

}
