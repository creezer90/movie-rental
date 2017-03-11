package pl.movie.rental.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class SearchCriteriaCommand {

	private String id_movie;
	private String title;
	private String country;
	private String genre;
	private int minPrice;
	private int maxPrice;

	public SearchCriteriaCommand() {
		this.id_movie = "";
		this.title = "";
		this.country = "";
		this.genre = "";
		this.minPrice = 0;
		this.maxPrice = Integer.MAX_VALUE;
	}

}
