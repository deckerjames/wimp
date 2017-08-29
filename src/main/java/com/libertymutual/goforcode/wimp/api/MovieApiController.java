package com.libertymutual.goforcode.wimp.api;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libertymutual.goforcode.wimp.models.Movie;
import com.libertymutual.goforcode.wimp.models.Movie;
import com.libertymutual.goforcode.wimp.repositories.MovieRepository;

import javassist.NotFoundException;

@RestController
@RequestMapping("/api/movies")
public class MovieApiController {
	
	private MovieRepository movieRepo;
	
	public MovieApiController(MovieRepository movieRepo) {
		this.movieRepo = movieRepo;
		
		movieRepo.save(new Movie("Pirates", "Disney"));
		movieRepo.save(new Movie("Pirates2", "Disney"));
		movieRepo.save(new Movie("Pirates3", "Disney"));
		movieRepo.save(new Movie("Pirates4", "Disney"));
	}
	
	@GetMapping("")
	public List<Movie> getAll() {
		return movieRepo.findAll();	
	}
	
	@GetMapping("{id}")
	public Movie getOne(@PathVariable long id) throws StuffNotFoundException {
		Movie movie = movieRepo.findOne(id);
		if (movie == null) {
			throw new StuffNotFoundException();
		}
		return movie;
	}
	
	@DeleteMapping("{id}")
	public Movie deleteOne(@PathVariable long id) {
		try {
			Movie movie = movieRepo.findOne(id);
			movieRepo.delete(id);
			return movie;
		} catch (EmptyResultDataAccessException erdae) {
			return null;			
		}
	}
		
	@PostMapping("")
	public Movie create(@RequestBody Movie movie) {
		return movieRepo.save(movie);		
	} 
	
	@PutMapping("{id}")
	public Movie update(@RequestBody Movie movie, @PathVariable long id) {
		movie.setId(id);
		return movieRepo.save(movie);
	}
}
