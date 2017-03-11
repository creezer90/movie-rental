package pl.movie.rental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.movie.rental.DTO.MovieDTO;
import pl.movie.rental.commands.GetMovieCommand;
import pl.movie.rental.commands.SearchCriteriaCommand;
import pl.movie.rental.model.Movie;
import pl.movie.rental.service.MovieService;
import pl.movie.rental.service.ObjectConverter;
import pl.movie.rental.service.impl.MovieServiceImpl;

@Controller
@RequestMapping("/")
public class StartController {

	@Autowired
	private MovieService movieServiceImpl;

	@Autowired
	private ObjectConverter<Movie, MovieDTO> movieConverter;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String view(ModelMap model, @ModelAttribute GetMovieCommand getMovieCommand,
			@ModelAttribute SearchCriteriaCommand searchCriteriaCommand) {
		model.addAttribute("searchCriteria", searchCriteriaCommand);
		model.addAttribute("movieContext",
				movieConverter.convert(movieServiceImpl.findMoviesByCriteria(getMovieCommand, searchCriteriaCommand)));
		return "view";
	}

	@RequestMapping(value = "/rentMovieView", method = RequestMethod.POST)
	public String rentView(ModelMap model, @RequestParam("id") Long id) {
		model.addAttribute("movie", movieServiceImpl.findMovieById(id));
		return "rentMovieView";
	}

}
