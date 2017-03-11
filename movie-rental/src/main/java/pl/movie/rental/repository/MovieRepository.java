package pl.movie.rental.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.movie.rental.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

	@Query(value = "select m FROM Movie m WHERE m.id_movie LIKE :id_movie AND m.title LIKE :title AND m.country"
			+ " LIKE :country AND m.genre LIKE :genre AND m.price >= :minPrice AND m.price"
			+ " <= :maxPrice", countQuery = "select count(m) from Movie m WHERE m.id_movie LIKE :id_movie "
					+ "AND m.title LIKE :title AND m.country LIKE :country AND m.genre LIKE :genre AND "
					+ "m.price >= :minPrice AND m.price <= :maxPrice")
	Page<Movie> findAllMovies(Pageable pageable, @Param("id_movie") String id_movie, @Param("title") String title,
			@Param("country") String country, @Param("genre") String genre, @Param("minPrice") int minPrice,
			@Param("maxPrice") int maxPrice);

	@Query(value = "SELECT m FROM Movie m LEFT JOIN FETCH m.rentPeriodList WHERE m.id = :id")
	Movie findMovieById(@Param("id") Long id);

}
