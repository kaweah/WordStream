package com.kaweah.wordstream.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
public class Concept {
	
	// Properties (member data)
	
	/*
	 *  The id property is annotated with @Id so that JPA recognizes it as the object’s ID.
	 *  It is also annotated with @GeneratedValue to indicate that the ID should be generated automatically.
	 */
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	protected Long id;

	protected String description;
	
	/* The 'mappedBy = "concept"' attribute specifies that
	 * the 'private Concept concept;' field in Word owns the
	 * relationship (i.e. contains the foreign key for the query to
	 * find all words for a concept.)
	 */
	
	// @OneToMany(mappedBy = "concept")
	@ManyToMany(mappedBy = "concepts")
	private Set <Word> words;
	
	// Constructors
	
	/* The default constructor exists only for the sake of JPA.
	 * It is not called directly, so it is designated as protected.
	 */
	
	protected Concept() {}
	
	public Concept(String description) {
		this.description = description;
	}
	
	// Accessors and mutators.
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	// Overridden Object functions.
	
	@Override
	public String toString() {
		return String.format(
	    		"Concept[id=%d, description='%s']", id, description);
	}

}
