package pl.movie.rental.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.mysema.query.types.QMap;

import pl.movie.rental.commands.GetMovieCommand;
import pl.movie.rental.commands.MovieSearchCriteriaCommand;
import pl.movie.rental.model.Movie;
import pl.movie.rental.model.QMovie;
import pl.movie.rental.model.criteria.service.MovieQueryFilterService;
import pl.movie.rental.repository.JoinDescriptor;
import pl.movie.rental.repository.MovieRepository;
import pl.movie.rental.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private MovieQueryFilterService movieQueryFiltrService;

	@Override
	public Movie addMovie(Movie movie) {
		return movieRepository.saveAndFlush(movie);
	}

	@Override
	public Movie findMovieById(Long id) {
		return movieRepository.findMovieById(id);
	}

	@Override
	public Page<Movie> findMoviesByCriteria(GetMovieCommand getMoviesCommand,
			MovieSearchCriteriaCommand searchCriteriaCommand) {
		return movieRepository.findAll(movieQueryFiltrService.toPredicate(searchCriteriaCommand),
				new PageRequest(getMoviesCommand.getPageNumber(), getMoviesCommand.getPageSize(),
						new Sort(Direction.valueOf(getMoviesCommand.getSortDirection()), getMoviesCommand.getSortBy(),
								getMoviesCommand.getLastSortBy())),
				JoinDescriptor.leftJoin(QMovie.movie.rentPeriodList));
	}

	@Override
	public List<Movie> findAllMovies() {
		return movieRepository.findAll();
	}

}
