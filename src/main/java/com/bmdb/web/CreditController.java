package com.bmdb.web;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
		if (credits.size()>0) {
			jr = JsonResponse.getInstance(credits);
		}
		else {
			jr = JsonResponse.getErrorInstance("No Credits found.");
		}
		return jr;
	}

	// get method
		@GetMapping("/{id}")
		public JsonResponse get(@PathVariable int id) {
			JsonResponse jr = null;
			Optional<Credit> credit = creditRepo.findById(id);
			if (credit.isPresent()) {
				jr = JsonResponse.getInstance(credit.get());
			} else {
				jr = JsonResponse.getErrorInstance("No credit found for ID: " + id);
			}

			return jr;
		}

	// 'create' method
		@PostMapping("/")
		public JsonResponse createCredit(@RequestBody Credit credit) {
			JsonResponse jr = null;

			try {
				credit = creditRepo.save(credit);
				jr = JsonResponse.getInstance(credit);
			} catch (DataIntegrityViolationException dive) {
				jr = JsonResponse.getErrorInstance(dive.getRootCause().getMessage());
				dive.printStackTrace();
			} catch (Exception e) {
				jr = JsonResponse.getErrorInstance("Error creating credit: " + e.getMessage());
				e.printStackTrace();
			}

			return jr;
		}

	// update method
		@PutMapping("/")
		public JsonResponse updateCredit(@RequestBody Credit credit) {
			JsonResponse jr = null;

			try {
				credit = creditRepo.save(credit);
				jr = JsonResponse.getInstance(credit);
			} catch (Exception e) {
				jr = JsonResponse.getErrorInstance("Error updating credit: " + e.getMessage());
				e.printStackTrace();
			}

			return jr;
		}

	//delete method
		@DeleteMapping("/{id}")
		public JsonResponse deleteCredit(@PathVariable int id) {
			JsonResponse jr = null;

			try {
				creditRepo.deleteById(id);
				jr = JsonResponse.getInstance(id);
			} catch (Exception e) {
				jr = JsonResponse.getErrorInstance("Error deleting credit: " + e.getMessage());
				e.printStackTrace();
			}

			return jr;
		}

	}
