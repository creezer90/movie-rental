package pl.movie.rental.model.criteria.service;



import com.mysema.query.types.Predicate;

import pl.movie.rental.commands.MovieSearchCriteriaCommand;

public interface MovieQueryFilterService {

	Predicate toPredicate(MovieSearchCriteriaCommand movieCriteriaCommand);
}
