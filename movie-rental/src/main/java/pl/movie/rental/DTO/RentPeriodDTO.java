package pl.movie.rental.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.movie.rental.model.Movie;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class RentPeriodDTO {

	private Long id;
	private Movie movie;
	private Date start_date;
	private Date end_date;

	@Override
	public String toString() {
		return "RentPeriod [id=" + id + ", start_date=" + start_date + ", end_date=" + end_date + "]";
	}

}
