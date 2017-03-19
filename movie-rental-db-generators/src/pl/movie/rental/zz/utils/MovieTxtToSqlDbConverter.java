package pl.movie.rental.zz.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class MovieTxtToSqlDbConverter {

	public static void main(String[] args) {
		int index = 0;

		File input = new File("filmy.txt");
		File output = new File("data.sql");
		int i = 0;

		try (BufferedReader br = new BufferedReader(new java.io.FileReader(input));

				BufferedWriter bw = new BufferedWriter(new FileWriter(output));) {

			StringBuilder sb = new StringBuilder();
			String line = "";
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] splitted = line.split("\t");

				sb.append("insert into movie (id, code, title, country, genre, price) values (").append(++i)
						.append(", '").append(splitted[0]).append("', '").//
						append(splitted[1]).append("', '").append(splitted[2]).append("', '").append(splitted[3])
						.append("', ").append(splitted[4]).append(");\n");

				bw.write(sb.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
