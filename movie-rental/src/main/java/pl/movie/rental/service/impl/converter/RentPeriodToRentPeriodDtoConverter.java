package pl.movie.rental.service.impl.converter;

import org.springframework.stereotype.Service;

import pl.movie.rental.DTO.RentPeriodDTO;
import pl.movie.rental.model.RentPeriod;
import pl.movie.rental.service.ObjectConverter;

@Service
public class RentPeriodToRentPeriodDtoConverter implements ObjectConverter<RentPeriod, RentPeriodDTO> {

	@Override
	public RentPeriodDTO convert(RentPeriod model) {
		return RentPeriodDTO.builder()//
				.id(model.getId())//
				.movie(model.getMovie())//
				.start_date(model.getStart_date())//
				.end_date(model.getEnd_date()).build();
	}

}
