package pl.movie.rental.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import pl.movie.rental.model.RentPeriod;
import pl.movie.rental.service.RentPeriodService;

public class RentPeriodServiceImpl implements RentPeriodService {

	@Autowired
	private RentPeriodService rentPeriodService;

	@Override
	public List<RentPeriod> findAll() {
		return null;
	}

	@Override
	public RentPeriod save() {
		return null;
	}

}
