package pl.movie.rental.service.impl.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.movie.rental.DTO.MovieDTO;
import pl.movie.rental.DTO.RentPeriodDTO;
import pl.movie.rental.model.Movie;
import pl.movie.rental.model.RentPeriod;
import pl.movie.rental.service.ObjectConverter;
import pl.movie.rental.service.impl.converter.MovieToMovieDtoConverter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MovieToMovieDtoConverterTest.MovieToMovieDtoConverterTestConfiguration.class)
public class MovieToMovieDtoConverterTest {

	private static final DateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	private ObjectConverter<Movie, MovieDTO> movieToMovieDtoConverter;

	List<Movie> movies;

	@Before
	public void init() throws ParseException {
		Movie m1 = new Movie(1L, "AA1993", "Lista Schindlera", "Polska", "wojenny", 6, true, new LinkedList<>());
		Movie m2 = new Movie(2L, "AA1994", "Pulp Fiction", "USA", "thriller", 6, true, new LinkedList<>());
		Movie m3 = new Movie(3L, "AA1995", "Siedem", "USA", "Thriller", 6, true, new LinkedList<>());
		Movie m4 = new Movie(4L, "AA1998", "Szeregowiec Ryan", "USA", "Wojenny", 6, true, new LinkedList<>());

		RentPeriod r1 = new RentPeriod(1L, m1, FORMATTER.parse("2017-01-12"), FORMATTER.parse("2017-02-01"));
		RentPeriod r2 = new RentPeriod(2L, m1, FORMATTER.parse("2017-02-12"), FORMATTER.parse("2017-03-01"));
		RentPeriod r3 = new RentPeriod(3L, m2, FORMATTER.parse("2017-03-12"), FORMATTER.parse("2017-04-01"));
		RentPeriod r4 = new RentPeriod(4L, m2, FORMATTER.parse("2017-04-12"), FORMATTER.parse("2017-05-01"));
		RentPeriod r5 = new RentPeriod(5L, m3, FORMATTER.parse("2017-05-12"), FORMATTER.parse("2017-06-01"));
		RentPeriod r6 = new RentPeriod(6L, m3, FORMATTER.parse("2017-06-12"), FORMATTER.parse("2017-07-01"));
		RentPeriod r7 = new RentPeriod(7L, m4, FORMATTER.parse("2017-07-12"), FORMATTER.parse("2017-08-01"));
		RentPeriod r8 = new RentPeriod(8L, m4, FORMATTER.parse("2017-08-12"), FORMATTER.parse("2017-09-01"));

		m1.getRentPeriodList().addAll(Arrays.asList(r1, r2));
		m2.getRentPeriodList().addAll(Arrays.asList(r3, r4));
		m3.getRentPeriodList().addAll(Arrays.asList(r5, r6));
		m4.getRentPeriodList().addAll(Arrays.asList(r7, r8));

		movies = new LinkedList<>(Arrays.asList(m1, m2, m3, m4));
	}

	@Test
	public void shouldConvertMovieToMovieDto() {
		// given
		Movie movie = movies.get(0);
		// when
		MovieDTO result = movieToMovieDtoConverter.convert(movie);
		// then
		Assert.assertEquals("AA1993", result.getCode());
		Assert.assertEquals(Long.valueOf(1L), result.getId());
		Assert.assertEquals("2017-03-01", FORMATTER.format(movie.getRentPeriodList().get(1).getEnd_date()));

	}

	public void shouldConvertCollectionOfMoviesToCollectionOfMoviesDto() {
		// given - movies
		// when
		Collection<MovieDTO> result = movieToMovieDtoConverter.convert(movies);
		// then
		Assert.assertEquals(4, result.size());
		Assert.assertEquals("Siedem", movies.get(2).getTitle());
		Assert.assertEquals(Long.valueOf(7L), movies.get(3).getRentPeriodList().get(1).getId());

	}

	@Configuration
	public static class MovieToMovieDtoConverterTestConfiguration {
		@Bean
		public ObjectConverter<Movie, MovieDTO> movieToMovieDtoConverter() {
			return new MovieToMovieDtoConverter();
		}

		@Bean
		public ObjectConverter<RentPeriod, RentPeriodDTO> rentPeriodToRentPeriodDtoConverter() {
			return new RentPeriodToRentPeriodDtoConverter();
		}

	}

}
