package pl.movie.rental.service.impl.converter;

import org.springframework.stereotype.Service;

import pl.movie.rental.DTO.MovieDTO;
import pl.movie.rental.model.Movie;
import pl.movie.rental.service.ObjectConverter;

@Service
public class MovieToMovieDtoConverter implements ObjectConverter<Movie, MovieDTO> {

	@Override
	public MovieDTO convert(Movie model) {
		return MovieDTO.builder()//
				.id(model.getId())//
				.id_movie(model.getId_movie())//
				.title(model.getTitle())//
				.country(model.getCountry())//
				.genre(model.getGenre())//
				.price(model.getPrice())//
				.is_available(model.getIs_available())//
				.rentPeriodList(model.getRentPeriodList()).build();
	}

}
