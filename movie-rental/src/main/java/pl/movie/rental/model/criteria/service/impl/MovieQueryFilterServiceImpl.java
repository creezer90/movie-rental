package pl.movie.rental.model.criteria.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;

import pl.movie.rental.DTO.RentPeriodDTO;
import pl.movie.rental.commands.MovieSearchCriteriaCommand;
import pl.movie.rental.model.QMovie;
import pl.movie.rental.model.RentPeriod;
import pl.movie.rental.model.criteria.service.MovieQueryFilterService;

@Service
public class MovieQueryFilterServiceImpl implements MovieQueryFilterService {

	private List<BooleanExpression> expressions;

	@Override
	public Predicate toPredicate(MovieSearchCriteriaCommand mscc) {
		expressions = new ArrayList<>();

		expressions.add(QMovie.movie.rentPeriodList.size().goe(0));

		if (!StringUtils.isEmpty(mscc.getId_movie())) {
			expressions.add(QMovie.movie.id_movie.containsIgnoreCase(mscc.getId_movie()));
		}
		if (!StringUtils.isEmpty(mscc.getTitle())) {
			expressions.add(QMovie.movie.title.containsIgnoreCase(mscc.getTitle()));
		}
		if (!StringUtils.isEmpty(mscc.getCountry())) {
			expressions.add(QMovie.movie.country.containsIgnoreCase(mscc.getCountry()));
		}
		if (!StringUtils.isEmpty(mscc.getGenre())) {
			expressions.add(QMovie.movie.genre.containsIgnoreCase(mscc.getGenre()));
		}
		Optional.ofNullable(mscc.getMinPrice()).ifPresent(c -> expressions.add(QMovie.movie.price.goe(c)));
		Optional.ofNullable(mscc.getMaxPrice()).ifPresent(c -> expressions.add(QMovie.movie.price.loe(c)));

		return BooleanExpression.allOf(expressions.toArray(new BooleanExpression[1]));
	}

}
