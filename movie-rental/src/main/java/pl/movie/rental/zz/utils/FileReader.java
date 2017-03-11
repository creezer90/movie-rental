package pl.movie.rental.zz.utils;

import java.util.List;

import pl.movie.rental.model.Movie;

public interface FileReader {

	List<Movie> getMoviesFromFileWithRandomAvailability(int numberOfMovies);

}
