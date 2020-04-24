package com.bmdb.web;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.bmdb.business.Actor;
import com.bmdb.business.JsonResponse;

import com.bmdb.db.ActorRepository;

@RestController
@RequestMapping("/actors")
public class ActorController {
	@Autowired
	private ActorRepository actorRepo;

	@GetMapping("/")
	public JsonResponse list() {
		JsonResponse jr = null;
		List<Actor> actors = actorRepo.findAll();
		if (actors.size() > 0) {
			jr = JsonResponse.getInstance(actors);
		} else {
			jr = JsonResponse.getErrorInstance("No Actors found.");
		}
		return jr;
	}
	//sort born before date, needs debugged
	@GetMapping("/list/{birthDate}")
	public JsonResponse findByBirthdate(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)@PathVariable LocalDate birthdate) {
		JsonResponse jr = null;
		List<Actor> actors = actorRepo.findAllByBirthdateBefore(birthdate);
		if (actors.size() > 0) {
			jr = JsonResponse.getInstance(actors);
		} else {
			jr = JsonResponse.getErrorInstance("No Actors found.");
		}
		return jr;
	}
	
	@GetMapping("/{id}")
	public JsonResponse get(@PathVariable int id) {
		JsonResponse jr = null;
		Optional<Actor> actor = actorRepo.findById(id);
		if (actor.isPresent()) {
			jr = JsonResponse.getInstance(actor.get());
		} else {
			jr = JsonResponse.getErrorInstance("No Actor found for ID: " + id);
		}
		return jr;
	}

	// 'create' method
	@PostMapping("/")
	public JsonResponse createActor(@RequestBody Actor actor) {
		JsonResponse jr = null;

		try {
			actor = actorRepo.save(actor);
			jr = JsonResponse.getInstance(actor);
		} catch (DataIntegrityViolationException dive) {
			jr = JsonResponse.getErrorInstance(dive.getRootCause().getMessage());
			dive.printStackTrace();
		} catch (Exception e) {
			jr = JsonResponse.getErrorInstance("Error creating actor: " + e.getMessage());
			e.printStackTrace();
		}

		return jr;

	}

	// update method
	@PutMapping("/")
	public JsonResponse updateActor(@RequestBody Actor actor) {
		JsonResponse jr = null;

		try {
			actor = actorRepo.save(actor);
			jr = JsonResponse.getInstance(actor);
		} catch (Exception e) {
			jr = JsonResponse.getErrorInstance("Error updating actor: " + e.getMessage());
			e.printStackTrace();
		}

		return jr;
	}

	// delete method
	@DeleteMapping("/{id}")
	public JsonResponse deleteActor(@PathVariable int id) {
		JsonResponse jr = null;

		try {
			actorRepo.deleteById(id);
			jr = JsonResponse.getInstance(id);
		} catch (Exception e) {
			jr = JsonResponse.getErrorInstance("Error deleting actor: " + e.getMessage());
			e.printStackTrace();
		}

		return jr;
	}

}
