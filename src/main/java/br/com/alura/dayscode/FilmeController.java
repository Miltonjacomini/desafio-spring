package br.com.alura.dayscode;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilmeController {

    private ListOfMovies movies = new ListOfMovies(new ArrayList<>());

    @Autowired
    private ImdbApiClient imdbApiClient;

    @GetMapping("/top250")
    public ListOfMovies getTop250Filmes(@RequestParam(required = false) String title) throws FileNotFoundException {

        ListOfMovies response = this.imdbApiClient.getBody();

        if (Objects.nonNull(title)) {
            this.movies.items().addAll(response.items().stream()
                    .filter(movie -> movie.title.contains(title))
                    .collect(Collectors.toList()));
        } else {
            this.movies.items.addAll(response.items());
        }

        PrintWriter writer = new PrintWriter("src/main/resources/content.html");
        new HTMLGenerator(writer).generate(movies);
        writer.close();

        return movies;
    }

    public record Movie(String id, String title, String image, String year, String imDbRating){}
    public record ListOfMovies(List<Movie> items){}

}
