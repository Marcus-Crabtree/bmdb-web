package com.bmdb.web;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bmdb.business.Movie;
import com.bmdb.db.MovieRepository;

@RestController
@RequestMapping("/movies")
public class MovieController {
	@Autowired
	private MovieRepository movieRepo;
	
	@GetMapping("/")
	public List<Movie> list() {
		List<Movie> movies = movieRepo.findAll();
		return movies;
	}
	@GetMapping("/{id}")
	public Movie get(@PathVariable int id) {
		Optional<Movie> movie = movieRepo.findById(id);
		return movie.get();
	}
}
