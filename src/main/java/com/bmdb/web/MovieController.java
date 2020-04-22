package com.bmdb.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bmdb.business.JsonResponse;
import com.bmdb.business.Movie;
import com.bmdb.db.MovieRepository;

@RestController
@RequestMapping("/movies")
public class MovieController {
	@Autowired
	private MovieRepository movieRepo;

	@GetMapping("/")
	public JsonResponse list() {
		JsonResponse jr = null;
		List<Movie> movies = movieRepo.findAll();
		if (movies.size() == 0) {
			jr = JsonResponse.getInstance("No Movies found.");
		} else {
			jr = JsonResponse.getInstance(movies);
		}
		return jr;
	}

	@GetMapping("/{id}")
	public JsonResponse get(@PathVariable int id) {
		JsonResponse jr = null;
		Optional<Movie> movie = movieRepo.findById(id);
		if (movie.isPresent()) {
			jr = JsonResponse.getInstance(movie.get());
		} else {
			jr = JsonResponse.getInstance("No Movie found for ID: " + id);
		}

		return jr;
	}

// 'create' method
	@PostMapping("/")
	public JsonResponse createMovie(@RequestBody Movie movie) {
		JsonResponse jr = null;

		try {
			movie = movieRepo.save(movie);
			jr = JsonResponse.getInstance(movie);
		} catch (Exception e) {
			jr = JsonResponse.getInstance("Error creating movie: " + e.getMessage());
			e.printStackTrace();
		}

		return jr;
	}

// update method
	@PutMapping("/")
	public JsonResponse updateMovie(@RequestBody Movie movie) {
		JsonResponse jr = null;

		try {
			movie = movieRepo.save(movie);
			jr = JsonResponse.getInstance(movie);
		} catch (Exception e) {
			jr = JsonResponse.getInstance("Error updating movie: " + e.getMessage());
			e.printStackTrace();
		}

		return jr;
	}
//delete method
	@DeleteMapping("/{id}")
	public JsonResponse deleteMovie(@PathVariable int id) {
		JsonResponse jr = null;

		try {
			 movieRepo.deleteById(id);
			jr = JsonResponse.getInstance(id);
		} catch (Exception e) {
			jr = JsonResponse.getInstance("Error deleting movie: " + e.getMessage());
			e.printStackTrace();
		}

		return jr;
	}
	
	
	
	
	
	
}
