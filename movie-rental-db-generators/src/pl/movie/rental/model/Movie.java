package pl.movie.rental.model;

import java.util.List;

public class Movie {

	private Long id;
	private String id_movie;
	private String title;
	private String country;
	private String genre;
	private int price;
	private Boolean is_available;

	private List<RentPeriod> rentPeriodList;

}
