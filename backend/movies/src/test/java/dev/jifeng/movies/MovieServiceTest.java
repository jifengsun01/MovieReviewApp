package dev.jifeng.movies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAllMovies() {
        // given
        Movie movie1 = new Movie();
        movie1.setImdbId("tt1234");
        Movie movie2 = new Movie();
        movie2.setImdbId("tt5678");

        given(movieRepository.findAll()).willReturn(Arrays.asList(movie1, movie2));

        // when
        List<Movie> allMovies = movieService.allMovies();

        // then
        assertThat(allMovies).hasSize(2);
        assertThat(allMovies.get(0).getImdbId()).isEqualTo("tt1234");
    }

    @Test
    void testSingleMovie() {
        // given
        Movie movie = new Movie();
        movie.setImdbId("tt1234");
        given(movieRepository.findMovieByImdbId("tt1234")).willReturn(Optional.of(movie));

        // when
        Optional<Movie> foundMovie = movieService.singleMovie("tt1234");

        // then
        assertThat(foundMovie).isPresent();
        assertThat(foundMovie.get().getImdbId()).isEqualTo("tt1234");
    }
}
