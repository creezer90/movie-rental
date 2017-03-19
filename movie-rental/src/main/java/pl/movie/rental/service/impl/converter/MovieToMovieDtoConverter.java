package pl.movie.rental.service.impl.converter;

import java.util.LinkedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.movie.rental.DTO.MovieDTO;
import pl.movie.rental.DTO.RentPeriodDTO;
import pl.movie.rental.model.Movie;
import pl.movie.rental.model.RentPeriod;
import pl.movie.rental.service.ObjectConverter;

@Service
public class MovieToMovieDtoConverter implements ObjectConverter<Movie, MovieDTO> {

	@Autowired
	ObjectConverter<RentPeriod, RentPeriodDTO> rentPeriodConverter;

	@Override
	public MovieDTO convert(Movie model) {

		return MovieDTO.builder()//
				.id(model.getId())//
				.code(model.getCode())//
				.title(model.getTitle())//
				.country(model.getCountry())//
				.genre(model.getGenre())//
				.price(model.getPrice())//
				.rentPeriodList(new LinkedList<>(rentPeriodConverter.convert(model.getRentPeriodList()))).build();//

	}

}
