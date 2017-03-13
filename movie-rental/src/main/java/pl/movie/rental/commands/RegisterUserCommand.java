package pl.movie.rental.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserCommand {

	private String name;
	private String email;
	private String password;
	private String passwordRepeated;
}
