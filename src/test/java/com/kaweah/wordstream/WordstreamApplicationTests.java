package com.kaweah.wordstream;

import java.util.List;
import java.util.logging.Logger;

// import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.kaweah.wordstream.model.Concept;
import com.kaweah.wordstream.model.Language;
import com.kaweah.wordstream.model.Word;
import com.kaweah.wordstream.repository.ConceptRepository;
import com.kaweah.wordstream.repository.LanguageRepository;
import com.kaweah.wordstream.repository.WordRepository;

@SpringBootTest
class WordstreamApplicationTests {

    Logger log = Logger.getLogger(this.getClass().getName());
    
	@Autowired
    private WordRepository wordRepo;
	@Autowired
    private LanguageRepository languageRepo;
	@Autowired
    private ConceptRepository conceptRepo;
    
	@Test
	void contextLoads() {
	}
/*
    @Test
    @Transactional
    public void testByTitle() {
        log.info("... testByTitle ...");
 
        List <Word> wl = wordRepo.findByText("airyaman");
        for (Word w : wl) {
        	log.info("Found this: " + w.toString());
        }
        Assert.assertEquals("Bad ID: " + wl.get(0).getId(), (long)1, (long)wl.get(0).getId());
    }
*/
	@Test
	@Transactional
	public void testPersistWordInLanguage() {
		log.info("... testPersistBook ...");
 
    	Language oldAvestan = new Language("Old Avestan");
    	
    	languageRepo.save(oldAvestan);

		Concept airyaman = new Concept("friend, companion");
		Concept adversary = new Concept("adversary");
		
		conceptRepo.save(airyaman);
		conceptRepo.save(adversary);

		Word w = new Word("aurvaθanąm", oldAvestan, adversary);
		log.info(w.toString());
		wordRepo.save(w);
	}
}
