package pl.movie.rental.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieDTO {

	private Long id;
	private String id_movie;
	private String title;
	private String country;
	private String genre;
	private int price;
	private Boolean is_available;

}