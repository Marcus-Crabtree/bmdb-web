package com.bmdb.web;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bmdb.business.Credit;

import com.bmdb.db.CreditRepository;
@RestController
@RequestMapping("/credits")
public class CreditController {
	@Autowired
	private CreditRepository creditRepo;

	@GetMapping("/")
	public List<Credit> list() {
		List<Credit> credits = creditRepo.findAll();
		return credits;
	}

	@GetMapping("/{id}")
	public Credit get(@PathVariable int id) {
		Optional<Credit> credit = creditRepo.findById(id);
		return credit.get();
	}
}