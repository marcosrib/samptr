package br.com.samptr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.samptr.models.LinkedEntity;
import br.com.samptr.services.LinkedService;

@RestController
@RequestMapping("linked")
public class LinkedController {
	
	@Autowired
	private LinkedService service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public LinkedEntity create(@RequestBody LinkedEntity entity) {
		return service.save(entity);
	}
}
