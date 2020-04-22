package com.bmdb.web;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bmdb.business.Credit;
import com.bmdb.business.JsonResponse;
import com.bmdb.db.CreditRepository;
@RestController
@RequestMapping("/credits")
public class CreditController {
	@Autowired
	private CreditRepository creditRepo;

	@GetMapping("/")
	public JsonResponse list() {
		JsonResponse jr = null;
		List<Credit> credits = creditRepo.findAll();
		if (credits.size()==0) {
			jr = JsonResponse.getInstance("No Credits found.");
		}
		else {
			jr = JsonResponse.getInstance(credits);
		}
		return jr;
	}

	@GetMapping("/{id}")
	public JsonResponse get(@PathVariable int id) {
		JsonResponse jr = null;
		Optional<Credit> credit = creditRepo.findById(id);
		if (credit.isPresent()) {
			jr = JsonResponse.getInstance(credit.get());
		}
		else {
			jr = JsonResponse.getInstance("No Credit found for ID: "+id);
		}
		return jr;
	}
}