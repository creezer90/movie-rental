package pl.movie.rental.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.movie.rental.commands.GetMovieCommand;
import pl.movie.rental.commands.SearchCriteriaCommand;
import pl.movie.rental.model.Movie;
import pl.movie.rental.repository.MovieRepository;
import pl.movie.rental.service.MovieService;
import pl.movie.rental.service.impl.MovieServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MovieServiceTest.MovieServiceConfigurationTest.class)
public class MovieServiceTest {

	@Autowired
	private MovieService movieService;

	@Autowired
	private MovieRepository mockoMovieRepository;

	private List<Movie> movieList;

	@Before
	public void init() {
		movieList = new ArrayList<>(Arrays.asList(//
				new Movie(1L, "AA1993", "Lista Schindlera", "Polska", "wojenny", 6, true, null),
				new Movie(2L, "AA1994", "Pulp Fiction", "USA", "thriller", 6, true, null),
				new Movie(3L, "AA1995", "Siedem", "USA", "Thriller", 6, true, null),
				new Movie(4L, "AA1998", "Szeregowiec Ryan", "USA", "Wojenny", 6, true, null),
				new Movie(5L, "AA2000", "Chlopaki nie placza", "Polska", "komedia", 6, true, null),
				new Movie(6L, "AA2002", "Resident Evil", "Wielka Brytania", "horror", 6, true, null),
				new Movie(7L, "AA2003", "Kill Bill", "USA", "thriller", 6, true, null)));

	}

	@Test
	public void shouldGetAllMovies() {
		// given
		Mockito.when(mockoMovieRepository.findAllMovies(Mockito.any(Pageable.class), Mockito.anyString(),
				Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt()))
				.thenReturn(new PageImpl<>(movieList));
		// when
		Page<Movie> page = movieService.findMoviesByCriteria(new GetMovieCommand(), new SearchCriteriaCommand());
		List<Movie> result = page.getContent();

		// then

		Assert.assertEquals(7, result.size());
		;

	}

	@Test
	public void shouldAddMovie() {
		// given
		Movie toSave = new Movie(8L, "AA2006", "Charlie i fabryka czekolady", "USA", "familijny", 8, true, null);
		int sizeBeforeAdd = movieList.size();
		Mockito.when(mockoMovieRepository.saveAndFlush(Mockito.any(Movie.class))).then(new Answer<Movie>() {

			@Override
			public Movie answer(InvocationOnMock invocation) throws Throwable {
				movieList.add(toSave);
				return toSave;
			}
		});
		// when

		movieService.addMovie(toSave);

		// then
		Assert.assertEquals(sizeBeforeAdd + 1, movieList.size());

	}

	@Configuration
	public static class MovieServiceConfigurationTest {
		@Bean
		public MovieService movieService() {
			return new MovieServiceImpl();
		}

		@Bean
		public MovieRepository movieRepository() {
			return Mockito.mock(MovieRepository.class);
		}
	}
}
