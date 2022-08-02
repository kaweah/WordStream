package com.kaweah.wordstream.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Basic language content node.
 * 
 * The Word class is annotated with @Entity, indicating that it is a JPA entity.
 * Because no @Table annotation exists, it is assumed that this entity is mapped to a table named Word.
 * 
 * @author Dan Jensen (Kaweah)
 *
 */

@Entity
public class Word {

	// Properties (member data)
	
	/*
	 *  The id property is annotated with @Id so that JPA recognizes it as the object’s ID.
	 *  It is also annotated with @GeneratedValue to indicate that the ID should be generated automatically.
	 */
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	protected Long id;
	protected String text;

	/* "Many-to-one" here means that the affected property (language) may be shared by
	 * multiple instances of the present class (Word).
	 * 
	 * The join will be established with the class of the affected property (language)
	 * by way of the WORD table column "LANGUAGE_ID"
	 * 
	 * The WORD table does not contain a language column, but 
	 * a LANGUAGE_ID column with a foreign key.
	 * 
	 * This @JoinColumn annotation creates a join to
	 * lazily fetch the Language instance that "owns" this word.
	 */
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="LANGUAGE_ID")
	private Language language;
	
	// Constructors
	
	/* The default constructor exists only for the sake of JPA.
	 * It is not called directly, so it is designated as protected.
	 */
	
	protected Word() {}

	public Word(String text, Language language) {
		this.text = text;
		this.language = language;
	}

	// Accessors and mutators.

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String _text) {
		text = _text;
	}

	// Overridden Object functions.

	@Override
	public String toString() {
		return String.format(
	    		"Word[id=%d, text='%s']", id, text);
	}

}
