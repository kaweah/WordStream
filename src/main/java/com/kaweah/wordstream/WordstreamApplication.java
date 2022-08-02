package com.kaweah.wordstream;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

import com.kaweah.wordstream.model.Language;
import com.kaweah.wordstream.model.Word;
import com.kaweah.wordstream.repository.LanguageRepository;
import com.kaweah.wordstream.repository.WordRepository;

@SpringBootApplication
public class WordstreamApplication {

	private static final Logger log = LoggerFactory.getLogger(WordstreamApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WordstreamApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner word_demo(
			LanguageRepository languageRepo, 
			WordRepository wordRepo) {
	    return (args) -> {
	    	
	    	Language oldAvestan = new Language("Old Avestan");
	    	
	    	languageRepo.save(oldAvestan);
	    	 	
	    	wordRepo.save(new Word("airya", oldAvestan));
	    	wordRepo.save(new Word("airyanãm", oldAvestan));
	    	wordRepo.save(new Word("aurvaθana-", oldAvestan));
	    	wordRepo.save(new Word("aurvant-", oldAvestan));
			wordRepo.save(new Word("aēšma-", oldAvestan));
			wordRepo.save(new Word("aogah-", oldAvestan));
			wordRepo.save(new Word("aka-", oldAvestan));

	      // fetch all words
	      log.info("Words found with findAll():");
	      log.info("-------------------------------");
	      for (Word word : wordRepo.findAll()) {
	        log.info(word.toString());
	      }
	      log.info("");

	      // fetch words by text
	      log.info("Word found with findByText('aurvant-'):");
	      log.info("--------------------------------------------");
	      wordRepo.findByText("aurvant-").forEach(aurvant -> {
	        log.info(aurvant.toString());
	      });
	      log.info("");
	      
	      log.info("Words found with findByLanguage(oldAvestan):");
	      log.info("--------------------------------------------");
	      List<Word> words = wordRepo.findByLanguage(oldAvestan);
	      for (Word word : words) {
	    	  log.info(word.toString());
	      }
	      log.info("");
	    };
	}

	@Bean
	public CommandLineRunner language_demo(LanguageRepository repository) {
	    return (args) -> {
	      // save a few customers
	      repository.save(new Language("Younger Avestan 1"));
	      repository.save(new Language("Younger Avestan 2"));
	      repository.save(new Language("Vedic Sanskrit"));

	      // fetch all customers
	      log.info("Languages found with findAll():");
	      log.info("-------------------------------");
	      for (Language tongue : repository.findAll()) {
	        log.info(tongue.toString());
	      }
	      log.info("");

	      // fetch language by name
	      log.info("Language found with findByName('Old Avestan'):");
	      log.info("--------------------------------------------");
	      repository.findByName("Old Avestan").forEach(oldAvestan -> {
	        log.info(oldAvestan.toString());
	      });
	      log.info("");
	    };
	}

}
