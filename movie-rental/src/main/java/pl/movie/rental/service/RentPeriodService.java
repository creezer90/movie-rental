package pl.movie.rental.service;

import java.util.List;

import pl.movie.rental.model.RentPeriod;

public interface RentPeriodService {

	List<RentPeriod> findAll();

	RentPeriod save();

}
