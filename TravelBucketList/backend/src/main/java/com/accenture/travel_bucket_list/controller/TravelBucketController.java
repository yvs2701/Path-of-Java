package com.accenture.travel_bucket_list.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.travel_bucket_list.entity.TravelBucket;
import com.accenture.travel_bucket_list.repository.TravelBucketRepository;

@RestController
@RequestMapping(value = "/api/travelbuckets")
@CrossOrigin(origins = { "http://localhost:5173", "http://localhost:4173" })
public class TravelBucketController {
	private final TravelBucketRepository repository;

	@Autowired
	public TravelBucketController(TravelBucketRepository repository) {
		this.repository = repository;
	}

	@GetMapping(produces = { "application/json" })
	public List<TravelBucket> getAllBuckets() {
		return repository.findAll();
	}

	@PostMapping(consumes = { "application/json" }, produces = { "application/json" })
	public TravelBucket createBucket(@RequestBody TravelBucket bucket) {
		return repository.save(bucket);
	}
}
