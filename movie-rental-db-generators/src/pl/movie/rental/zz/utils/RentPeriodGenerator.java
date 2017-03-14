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

		int movieId = 1;

		long oneDay = 86400000;

		long dateFromLong = FORMATTER.parse(dateFrom).getTime();

		long dateToLong = FORMATTER.parse(dateTo).getTime();

		long periodBetweenDates = (dateToLong - dateFromLong);

		StringBuilder sb = new StringBuilder();

		while (movieId < 88) {

			long period = ((long) (Math.random() * 29) + 2) * oneDay;

			long startDate = dateFromLong + ((long) (Math.random() * (periodBetweenDates - period)));

			long endDate = startDate + period;

			sb.append("insert into rent_period (id, movie_id, start_date, end_date) values (").append(++index)
					.append(", ").append(movieId += (int) (Math.random() * 3) + 1).append(", '")
					.append(FORMATTER.format(new Date(startDate))).append("', '")
					.append(FORMATTER.format(new Date(endDate))).append("');\n");
		}
		return sb.toString();
	}

	public static void main(String[] args) throws ParseException {
		System.out.println(generateRecords("2017-02-02", "2017-03-04"));

	}
}
