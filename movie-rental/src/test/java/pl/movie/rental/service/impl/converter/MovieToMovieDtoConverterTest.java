package pl.movie.rental.service.impl.converter;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.movie.rental.DTO.MovieDTO;
import pl.movie.rental.model.Movie;
import pl.movie.rental.service.ObjectConverter;
import pl.movie.rental.service.impl.converter.MovieToMovieDtoConverter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MovieToMovieDtoConverterTest.MovieToMovieDtoConverterTestConfiguration.class)
public class MovieToMovieDtoConverterTest {

	@Autowired
	private ObjectConverter<Movie, MovieDTO> movieToMovieDtoConverter;

	@Test
	public void shouldConvertMovieToMovieDto() {
		// given
		Movie movie = new Movie(3L, "AA1995", "Siedem", "USA", "Thriller", 6, true);
		// when
		MovieDTO result = movieToMovieDtoConverter.convert(movie);
		// then
		Assert.assertEquals("AA1995", result.getId_movie());
		Assert.assertEquals(Long.valueOf(3L), result.getId());

	}

	public void shouldConvertCollectionOfMoviesToCollectionOfMoviesDto() {
		// given
		List<Movie> movies = Arrays.asList(//
				new Movie(1L, "AA1993", "Lista Schindlera", "Polska", "wojenny", 6, true),
				new Movie(2L, "AA1994", "Pulp Fiction", "USA", "thriller", 6, true),
				new Movie(3L, "AA1995", "Siedem", "USA", "Thriller", 6, true),
				new Movie(4L, "AA1998", "Szeregowiec Ryan", "USA", "Wojenny", 6, true));
		// when
		Collection<MovieDTO> result = movieToMovieDtoConverter.convert(movies);
		// then
		Assert.assertEquals(4, result.size());
		Assert.assertEquals("Siedem", movies.get(2).getTitle());

	}

	@Configuration
	public static class MovieToMovieDtoConverterTestConfiguration {
		@Bean
		public ObjectConverter<Movie, MovieDTO> movieToMovieDtoConverter() {
			return new MovieToMovieDtoConverter();
		}

	}

}
