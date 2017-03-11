package pl.movie.rental.zz.utils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class MovieTxtToSqlDbConverterNotImplemented {

	public static void main(String[] args) {
		int index = 0;

		File input = new File("filmy.txt");
		File output = new File("data.sql");
		int i = 0;

		try (BufferedReader br = new BufferedReader(new java.io.FileReader(input));

				BufferedWriter bw = new BufferedWriter(new FileWriter(output));) {

			String line = "";
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] splitted = line.split("\t");

				bw.write("insert into movie (id, id_movie, title, country, genre, price) values (" + ++i + ", " + "'"
						+ splitted[0] + "'" + ", " + "'" + splitted[1] + "'" + ", " + "'" + splitted[2] + "'" + ", "
						+ "'" + splitted[3] + "'" + ", " + splitted[4] + ");" + "\n");

				// System.out
				// .println("insert into movie (id, id_movie, title, country,
				// genre, price, is_available) values ("
				// + ++i + ", " + "'" + splitted[0] + "'" + ", " + "'" +
				// splitted[1] + "'" + ", " + "'"
				// + splitted[2] + "'" + ", " + "'" + splitted[3] + "'" + ", " +
				// splitted[4] + ", " + 1
				// + ");");

				// ID_filmu Tytul Kraj_produkcji Gatunek Cena_w_zl
				// AA1993 Lista Schindlera Polska wojenny 6

				// movies.add(new Movie(null, splitted[0], splitted[1],
				// splitted[2], splitted[3],
				// Integer.parseInt(splitted[4]), (int) (Math.random() * 3) % 2
				// == 0 ? true : false));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
