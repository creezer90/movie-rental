package pl.movie.rental.zz.utils;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import pl.movie.rental.model.Movie;

public class FileReaderImpl {

	public List<Movie> getMoviesFromFileWithRandomAvailability(int numberOfMovies) {

		if (numberOfMovies < 1) {
			throw new IllegalArgumentException("number of movies must be greater than 0");
		}

		List<Movie> movies = new ArrayList<>();
		int index = 0;

		File file = new File("filmy.txt");

		try (BufferedReader br = new BufferedReader(new java.io.FileReader(file))) {
			String line = "";
			br.readLine();
			while ((line = br.readLine()) != null && index++ <= numberOfMovies) {
				String[] splitted = line.split("\t");

				// movies.add(new Movie(null, splitted[0], splitted[1],
				// splitted[2], splitted[3],
				// Integer.parseInt(splitted[4]), (int) (Math.random() * 3) % 2
				// == 0 ? true : false));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return movies;
	}
}
