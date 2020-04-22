package com.bmdb.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import com.bmdb.business.JsonResponse;
import com.bmdb.business.Genre;
import com.bmdb.db.GenreRepository;

@RestController

// list method
@RequestMapping("/genres")
public class GenreController {
	@Autowired
	private GenreRepository genreRepo;

	@GetMapping("/")
	public JsonResponse list() {
		JsonResponse jr = null;
		List<Genre> genres = genreRepo.findAll();
		if (genres.size() > 0) {
			jr = JsonResponse.getInstance(genres);
		} else {
			jr = JsonResponse.getErrorInstance("No genres found.");
		}
		return jr;
	}

// get method
	@GetMapping("/{id}")
	public JsonResponse get(@PathVariable int id) {
		JsonResponse jr = null;
		Optional<Genre> genre = genreRepo.findById(id);
		if (genre.isPresent()) {
			jr = JsonResponse.getInstance(genre.get());
		} else {
			jr = JsonResponse.getErrorInstance("No genre found for ID: " + id);
		}

		return jr;
	}

// 'create' method
	@PostMapping("/")
	public JsonResponse createGenre(@RequestBody Genre genre) {
		JsonResponse jr = null;

		try {
			genre = genreRepo.save(genre);
			jr = JsonResponse.getInstance(genre);
		} catch (DataIntegrityViolationException dive) {
			jr = JsonResponse.getErrorInstance(dive.getRootCause().getMessage());
			dive.printStackTrace();
		} catch (Exception e) {
			jr = JsonResponse.getErrorInstance("Error creating genre: " + e.getMessage());
			e.printStackTrace();
		}

		return jr;
	}

// update method
	@PutMapping("/")
	public JsonResponse updateGenre(@RequestBody Genre genre) {
		JsonResponse jr = null;

		try {
			genre = genreRepo.save(genre);
			jr = JsonResponse.getInstance(genre);
		} catch (Exception e) {
			jr = JsonResponse.getErrorInstance("Error updating genre: " + e.getMessage());
			e.printStackTrace();
		}

		return jr;
	}

//delete method
	@DeleteMapping("/{id}")
	public JsonResponse deleteGenre(@PathVariable int id) {
		JsonResponse jr = null;

		try {
			genreRepo.deleteById(id);
			jr = JsonResponse.getInstance(id);
		} catch (Exception e) {
			jr = JsonResponse.getErrorInstance("Error deleting genre: " + e.getMessage());
			e.printStackTrace();
		}

		return jr;
	}

}
