package br.com.samptr.services;

import org.springframework.stereotype.Service;

import br.com.samptr.models.LinkedEntity;

@Service
public interface LinkedService {
	LinkedEntity save(LinkedEntity linkedEntity);
}
