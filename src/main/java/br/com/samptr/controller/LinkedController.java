package br.com.samptr.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.samptr.dtos.LinkedDTO;
import br.com.samptr.models.LinkedEntity;
import br.com.samptr.services.LinkedService;

@Controller
public class LinkedController {

	private static final String URL = "http://localhost:8080/download/";
	
	@Autowired
	private LinkedService service;
	
	@GetMapping(value = "/create-link")
	public String getLink() {
       return "redirect:/";
	}
	
	@PostMapping(value = "/create-link")
	public String create(@Validated LinkedDTO dto, BindingResult result, Model model) {
		LinkedEntity linkendEntity  = service.save(convertDTOToEntity(dto));
		LinkedDTO dtoConverted = convertEntityToDTO(linkendEntity);
		dtoConverted.setUrlDownload(URL + linkendEntity.getHashUri() );
		model.addAttribute("linked", dtoConverted);
		return "/index";
	}

	private LinkedEntity convertDTOToEntity(LinkedDTO dto) {
		return LinkedEntity.builder()
				.uri(dto.getUri())
				.build();
	}
	
	private LinkedDTO convertEntityToDTO(LinkedEntity entity) {
		return LinkedDTO.builder()
				.id(entity.getId())
				.build();
	}
	
	
}
