package pl.movie.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.movie.rental.model.RentPeriod;

public interface RentPeriodRepository extends JpaRepository<RentPeriod, Long> {

}
