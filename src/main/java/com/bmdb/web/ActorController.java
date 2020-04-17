package com.bmdb.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bmdb.business.Actor;
import com.bmdb.db.ActorRepository;

@RestController
@RequestMapping("/actors")
public class ActorController {
	@Autowired
	private ActorRepository actorRepo;
	
	@GetMapping("/")
	public List<Actor> list() {
		List<Actor> actors = actorRepo.findAll();
		return actors;
	}
	@GetMapping("/{id}")
	public Actor get(@PathVariable int id) {
		Optional<Actor> actor = actorRepo.findById(id);
		return actor.get();
	}
	

}
