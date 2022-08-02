package com.kaweah.wordstream.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Languages are only linked by way of their constituent words and characteristics,
 * so no direct links with other languages are provided.
 * 
 * The Language class is annotated with @Entity, indicating that it is a JPA entity.
 * Because no @Table annotation exists, it is assumed that this entity is mapped to a table named Language.
 * 
 * @author Dan Jensen (Kaweah)
 *
 */

@Entity
public class Language {
	
	// Properties (member data)
	
	/*
	 *  The id property is annotated with @Id so that JPA recognizes it as the object’s ID.
	 *  It is also annotated with @GeneratedValue to indicate that the ID should be generated automatically.
	 */
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	protected Long id;

	protected String name;
	
	/* The 'mappedBy = "language"' attribute specifies that
	 * the 'private Language language;' field in Word owns the
	 * relationship (i.e. contains the foreign key for the query to
	 * find all words for a language.)
	 */
	
	@OneToMany(mappedBy = "language")
	private Set <Word> words;
	
	// Constructors
	
	/* The default constructor exists only for the sake of JPA.
	 * It is not called directly, so it is designated as protected.
	 */
	
	protected Language() {}
	
	public Language(String name) {
		this.name = name;
	}
	
	// Accessors and mutators.
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String _name) {
		name = _name;
	}

	// Overridden Object functions.
	
	@Override
	public String toString() {
		return String.format(
	    		"Language[id=%d, name='%s']", id, name);
	}

}
