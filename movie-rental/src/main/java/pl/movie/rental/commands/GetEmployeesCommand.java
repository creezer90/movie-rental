package pl.movie.rental.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class GetEmployeesCommand {

	private String sortBy;
	private String lastSortBy;
	private String sortDirection;
	private int pageSize;
	private int pageNumber;

	public GetEmployeesCommand() {
		this.sortBy = "name";
		this.lastSortBy = "surname";
		this.sortDirection = "ASC";
		this.pageSize = 10;
		this.pageNumber = 0;
	}
}
