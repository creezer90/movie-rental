package pl.movie.rental.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import pl.movie.rental.DTO.MovieDTO;
import pl.movie.rental.DTO.PageDTO;
import pl.movie.rental.commands.SearchCriteriaCommand;
import pl.movie.rental.controller.StartController;
import pl.movie.rental.model.Movie;
import pl.movie.rental.repository.MovieRepository;
import pl.movie.rental.service.MovieService;
import pl.movie.rental.service.ObjectConverter;
import pl.movie.rental.service.impl.MovieServiceImpl;
import pl.movie.rental.service.impl.converter.MovieToMovieDtoConverter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = StartControllerTest.StartControllerTestConfiguration.class)
@WebAppConfiguration
public class StartControllerTest {

	private MockMvc mockMvc;

	private List<Movie> movies;

	private PageDTO<Movie> pageDto;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private MovieService movieService;

	@Autowired
	private MovieRepository mockMovieRepository;

	@Autowired
	private ObjectConverter<Movie, MovieDTO> mockConverter;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		movies = new ArrayList<>(Arrays.asList(//
				new Movie(1L, "AA1993", "Lista Schindlera", "Polska", "wojenny", 6, true),
				new Movie(2L, "AA1994", "Pulp Fiction", "USA", "thriller", 6, true),
				new Movie(3L, "AA1995", "Siedem", "USA", "Thriller", 6, true),
				new Movie(4L, "AA1998", "Szeregowiec Ryan", "USA", "Wojenny", 6, true)));
		pageDto = new PageDTO<Movie>();
		pageDto.setResult(movies);

		Mockito.when(mockConverter.convert(Mockito.any(Page.class))).thenReturn(pageDto);
	}

	@Test
	public void shouldGetStartPageWithModelAttributes() throws Exception {
		mockMvc.perform(get("/"))//
				.andExpect(status().isOk()).andExpect(view().name("view"))//
				.andExpect(forwardedUrl("/WEB-INF/views/view.jsp"))//
				.andExpect(model().attribute("searchCriteria", new SearchCriteriaCommand()))
				.andExpect(model().attribute("movieContext", pageDto));//
	}

	@Configuration
	@EnableWebMvc
	public static class StartControllerTestConfiguration extends WebMvcConfigurerAdapter {
		@Bean
		public StartController startController() {
			return new StartController();
		}

		@Bean
		public MovieService movieService() {
			return new MovieServiceImpl();
		}

		@Bean
		public ObjectConverter<Movie, MovieDTO> converter() {
			return Mockito.mock(MovieToMovieDtoConverter.class);
		}

		@Bean
		public MovieRepository movieRepository() {
			return Mockito.mock(MovieRepository.class);
		}

		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		}

		@Bean
		public ViewResolver viewResolver() {
			InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
			viewResolver.setPrefix("/WEB-INF/views/");
			viewResolver.setSuffix(".jsp");
			return viewResolver;
		}

	}

}
