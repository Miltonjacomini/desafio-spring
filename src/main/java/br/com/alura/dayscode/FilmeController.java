package br.com.alura.dayscode;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilmeController {

    public static final String POST_SUCCESS = "Filme inserido aos favoritos com sucesso!";
    public static final String POST_FAIL = "Filme não foi encontrado na lista";

    private ListOfMovies movies = new ListOfMovies(new ArrayList<>());
    private ListOfMovies favoritos = new ListOfMovies(new ArrayList<>());

    @Autowired
    private ImdbApiClient imdbApiClient;

    @GetMapping("/top250")
    public ListOfMovies getTop250Filmes(@RequestParam(required = false) String title) throws FileNotFoundException {

        this.movies.items.clear();
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

    @PostMapping("/favorito/{filmeId}")
    public String addFavorito(@PathVariable String filmeId) throws FileNotFoundException {

        if (this.movies.items.isEmpty()) {
            getTop250Filmes(null);
        }

        Optional<Movie> movieOp =
                this.movies.items.stream()
                        .filter(movie -> movie.id().equalsIgnoreCase(filmeId))
                        .findFirst();

        if (movieOp.isPresent()) {
            this.favoritos.items.add(movieOp.get());
            return POST_SUCCESS;
        } else {
            return POST_FAIL;
        }
    }

    public record Movie(String id, String title, String image, String year, String imDbRating){}
    public record ListOfMovies(List<Movie> items){}

}
