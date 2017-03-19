package pl.movie.rental.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieSearchCriteriaCommand {

	private String code;
	private String title;
	private String country;
	private String genre;
	private Integer minPrice;
	private Integer maxPrice;

}
