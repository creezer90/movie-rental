package pl.movie.rental.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import pl.movie.rental.commands.GetMovieCommand;
import pl.movie.rental.commands.SearchCriteriaCommand;
import pl.movie.rental.model.Movie;
import pl.movie.rental.repository.MovieRepository;
import pl.movie.rental.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	MovieRepository movieRepository;

	@Override
	public Movie addMovie(Movie movie) {
		return movieRepository.saveAndFlush(movie);
	}

	@Override
	public Movie findMovieById(Long id) {
		return movieRepository.findOne(id);
	}

	@Override
	public Page<Movie> findMoviesByCriteria(GetMovieCommand getMoviesCommand,
			SearchCriteriaCommand searchCriteriaCommand) {
		return movieRepository.findAllMovies(
				new PageRequest(getMoviesCommand.getPageNumber(), getMoviesCommand.getPageSize(),
						new Sort(Direction.valueOf(getMoviesCommand.getSortDirection()), getMoviesCommand.getSortBy(),
								getMoviesCommand.getLastSortBy())),
				"%" + searchCriteriaCommand.getId_movie() + "%", "%" + searchCriteriaCommand.getTitle() + "%",
				"%" + searchCriteriaCommand.getCountry() + "%", "%" + searchCriteriaCommand.getGenre() + "%",
				searchCriteriaCommand.getMinPrice(), searchCriteriaCommand.getMaxPrice());
	}

	@Override
	public List<Movie> findAllMovies() {
		return movieRepository.findAll();
	}

}
