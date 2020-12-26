package br.com.samptr.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.samptr.models.LinkedEntity;

public interface LinkedRepository extends JpaRepository<LinkedEntity, Long> {
	Optional<LinkedEntity> findByHashUri(String hashUri);
}
