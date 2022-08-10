package com.kaweah.wordstream.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kaweah.wordstream.model.Concept;
import com.kaweah.wordstream.model.Language;
import com.kaweah.wordstream.model.Word;

public interface WordRepository extends JpaRepository<Word, Long> {
	List<Word> findByText(String text);

	Word findById(long id);
	
	// Spring Data JPA implements a query according to a naming convention
	
	// utilized by this findByLanguage API. By doing so it creates the one-to-many join.
	
	List<Word> findByLanguage(Language language);
	
	/*
	 * Search for a particular concept across a table of words that each contain
	 * lists of concepts.
	 */
	
	List<Word> findByConcepts(Concept concept);
	
/*
    List<Word> findByTextContaining(String text);

    List<Word> findByTextLike(String text);

    List<Word> findByTextContains(String text);

    List<Word> findByTextIsContaining(String text);

    List<Word> findByTextStartsWith(String text);

    List<Word> findByTextEndsWith(String text);

    List<Word> findByTextContainingIgnoreCase(String text);

    List<Word> findByTextNotContaining(String text);

    List<Word> findByTextNotLike(String text);

    @Query("SELECT m FROM Word m WHERE m.text LIKE %:text%")
    List<Word> searchByTextLike(@Param("text") String text);

    @Query("SELECT m FROM Word m WHERE m.text LIKE ?1%")
    List<Word> searchByTextStartsWith(String text);

    //Escaping works in SpringBoot >= 2.4.1
    //@Query("SELECT m FROM Movie m WHERE m.director LIKE %?#{escape([0])} escape ?#{escapeCharacter()}")
    @Query("SELECT m FROM Word m WHERE m.text LIKE %:#{[0]}")
    List<Word> searchByTextEndsWith(String director);
    */
}