package com.bmdb.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import com.bmdb.business.MovieGenre;
import com.bmdb.business.JsonResponse;
import com.bmdb.db.MovieGenreRepository;
@RestController
@RequestMapping("/moviegenres")
public class MovieGenreController {
	@Autowired
	private MovieGenreRepository movieGenreRepo;

	@GetMapping("/")
	public JsonResponse list() {
		JsonResponse jr = null;
		List<MovieGenre> movieGenres = movieGenreRepo.findAll();
		if (movieGenres.size()>0) {
			jr = JsonResponse.getInstance(movieGenres);
		}
		else {
			jr = JsonResponse.getErrorInstance("No Movie Genres found.");
		}
		return jr;
	}

	// get method
		@GetMapping("/{id}")
		public JsonResponse get(@PathVariable int id) {
			JsonResponse jr = null;
			Optional<MovieGenre> movieGenre = movieGenreRepo.findById(id);
			if (movieGenre.isPresent()) {
				jr = JsonResponse.getInstance(movieGenre.get());
			} else {
				jr = JsonResponse.getErrorInstance("No Movie Genre found for ID: " + id);
			}

			return jr;
		}

	// 'create' method
		@PostMapping("/")
		public JsonResponse createMovieGenre(@RequestBody MovieGenre movieGenre) {
			JsonResponse jr = null;

			try {
				movieGenre = movieGenreRepo.save(movieGenre);
				jr = JsonResponse.getInstance(movieGenre);
			} catch (DataIntegrityViolationException dive) {
				jr = JsonResponse.getErrorInstance(dive.getRootCause().getMessage());
				dive.printStackTrace();
			} catch (Exception e) {
				jr = JsonResponse.getErrorInstance("Error creating Movie Genre: " + e.getMessage());
				e.printStackTrace();
			}

			return jr;
		}

	// update method
		@PutMapping("/")
		public JsonResponse updateMovieGenre(@RequestBody MovieGenre movieGenre) {
			JsonResponse jr = null;

			try {
				movieGenre = movieGenreRepo.save(movieGenre);
				jr = JsonResponse.getInstance(movieGenre);
			} catch (Exception e) {
				jr = JsonResponse.getErrorInstance("Error updating Moive Genre: " + e.getMessage());
				e.printStackTrace();
			}

			return jr;
		}

	//delete method
		@DeleteMapping("/{id}")
		public JsonResponse deleteMovieGenre(@PathVariable int id) {
			JsonResponse jr = null;

			try {
				movieGenreRepo.deleteById(id);
				jr = JsonResponse.getInstance(id);
			} catch (Exception e) {
				jr = JsonResponse.getErrorInstance("Error deleting Movie genre: " + e.getMessage());
				e.printStackTrace();
			}

			return jr;
		}
}


