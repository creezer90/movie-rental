package pl.movie.rental.zz.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RentPeriodGenerator {

	public static final DateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

	public static String generateRecords(String dateFrom, String dateTo) throws ParseException {

		if (dateFrom == null || dateFrom.isEmpty() || dateTo == null || dateTo.isEmpty()) {
			throw new IllegalArgumentException("args cannot be null or empty");
		}
		if ((FORMATTER.parse(dateTo).getTime() - FORMATTER.parse(dateFrom).getTime()) < (30L * 86400000)) {
			throw new IllegalArgumentException("minimal periods between dates must be greater than 30 days");
		}

		int index = 0;

		int movie_id = 1;

		long one_day = 86400000;

		long date_from = FORMATTER.parse(dateFrom).getTime();

		long date_to = FORMATTER.parse(dateTo).getTime();

		long period_between_dates = (date_to - date_from);

		StringBuilder sb = new StringBuilder();

		while (movie_id < 88) {

			long period = ((long) (Math.random() * 29) + 2) * one_day;

			long start_date = date_from + ((long) (Math.random() * (period_between_dates - period)));

			long end_date = start_date + period;

			sb.append("insert into rent_period (id, movie_id, start_date, end_date) values (").append(++index)
					.append(", ").append(movie_id += (int) (Math.random() * 3) + 1).append(", '")
					.append(FORMATTER.format(new Date(start_date))).append("', '")
					.append(FORMATTER.format(new Date(end_date))).append("');\n");
		}
		return sb.toString();
	}

	public static void main(String[] args) throws ParseException {
		System.out.println(generateRecords("2017-02-02", "2017-03-04"));

	}
}
