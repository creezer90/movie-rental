package pl.movie.rental.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.movie.rental.model.Movie;

public interface MovieRepository extends JoinFetchCapableRepository<Movie, Long> {

	@Query(value = "SELECT m FROM Movie m LEFT JOIN FETCH m.rentPeriodList WHERE m.id = :id")
	Movie findMovieById(@Param("id") Long id);

}
