package com.bmdb.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
		if (actors.size()==0) {
			jr = JsonResponse.getInstance("No Actors found.");
		}
		else {
			jr =JsonResponse.getInstance(actors);
		}
		return jr;
	}
	@GetMapping("/{id}")
	public JsonResponse get(@PathVariable int id) {
		JsonResponse jr = null;
		Optional<Actor> actor = actorRepo.findById(id);
		if (actor.isPresent()) {
			jr = JsonResponse.getInstance(actor.get());
		}
		else {
			jr = JsonResponse.getInstance("No Actor found for ID: "+id);
		}
		return jr;
	}
	

}
