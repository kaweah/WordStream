package com.kaweah.wordstream.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kaweah.wordstream.model.Language;

public interface LanguageRepository extends JpaRepository<Language, Long> {
	List<Language> findByName(String lastName);

	Language findById(long id);
}