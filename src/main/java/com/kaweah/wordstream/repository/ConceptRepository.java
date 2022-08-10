package com.kaweah.wordstream.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kaweah.wordstream.model.Concept;

public interface ConceptRepository extends JpaRepository<Concept, Long> {
	List<Concept> findByDescription(String description);

	Concept findById(long id);
}