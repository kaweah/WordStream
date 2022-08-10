package com.kaweah.wordstream;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

import com.kaweah.wordstream.model.*;
import com.kaweah.wordstream.repository.*;

@SpringBootApplication
public class WordstreamApplication {

	private static final Logger log = LoggerFactory.getLogger(WordstreamApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WordstreamApplication.class, args);
	}

	@Bean
	public CommandLineRunner word_demo(
			LanguageRepository languageRepo, 
			ConceptRepository conceptRepo, 
			WordRepository wordRepo) {
	    return (args) -> {
			
			Language oldAvestan = new Language("Old Avestan");
			Language protoIE = new Language("Proto Indo-European");
			Language protoII = new Language("Proto Indo-Iranian");
			Language vedic = new Language("Vedic");
			
			languageRepo.save(oldAvestan);
			languageRepo.save(protoIE);
			languageRepo.save(protoII);
			languageRepo.save(vedic);
			
			Concept airyaman = new Concept("friend, companion");
			Concept iran = new Concept("Iran");
			Concept iranian = new Concept("Iranian");
			Concept adversary = new Concept("adversary");
			Concept swift = new Concept("swift, runner, steed");
			Concept anger = new Concept("anger, fury, wrath");
			Concept power = new Concept("power, strength");
			Concept bad = new Concept("bad, evil, wicked");
			Concept evil_deity = new Concept("god to be rejected");
			Concept god = new Concept("god");
			Concept celestial = new Concept("celestial");
			
			conceptRepo.save(airyaman);
			conceptRepo.save(iran);
			conceptRepo.save(iranian);
			conceptRepo.save(adversary);
			conceptRepo.save(swift);
			conceptRepo.save(anger);
			conceptRepo.save(power);
			conceptRepo.save(bad);
			conceptRepo.save(evil_deity);
			conceptRepo.save(god);
			conceptRepo.save(celestial);

			Set <Concept> airya_concepts = new HashSet <> ();
			airya_concepts.add(iranian);

			Set <Concept> concepts_of_sky_god = new HashSet <> ();
			concepts_of_sky_god.add(god);
			concepts_of_sky_god.add(celestial);

			Set <Concept> devil_concepts = new HashSet <> ();
			devil_concepts.add(god);
			devil_concepts.add(bad);
			devil_concepts.add(evil_deity);

			Set <Concept> evil_concepts = new HashSet <> ();
			evil_concepts.add(bad);
			evil_concepts.add(evil_deity);

			wordRepo.save(new Word("airya", oldAvestan, airya_concepts));
			wordRepo.save(new Word("airyanãm", oldAvestan, iran));
			wordRepo.save(new Word("airyaman", oldAvestan, airyaman));
			wordRepo.save(new Word("aryaman", vedic, airyaman));
			wordRepo.save(new Word("aurvaθanąm", oldAvestan, adversary));
			wordRepo.save(new Word("aurvantem", oldAvestan, swift));
			wordRepo.save(new Word("aēšma", oldAvestan, anger));
			wordRepo.save(new Word("aogō", oldAvestan, power));
			wordRepo.save(new Word("deywós", protoIE, concepts_of_sky_god));
			wordRepo.save(new Word("daivá", protoII, concepts_of_sky_god));
			wordRepo.save(new Word("daēuua", oldAvestan, devil_concepts));
			wordRepo.save(new Word("akəm", oldAvestan, bad));
			
			// fetch all words
			log.info("");
			log.info("Words found with findAll():");
			log.info("-------------------------------");
			for (Word word : wordRepo.findAll()) {
				log.info(word.toString());
			}
			log.info("");

			// fetch words by text
			log.info("Word found with findByText('airyanãm'):");
			log.info("--------------------------------------------");
			wordRepo.findByText("airyanãm").forEach(aurvant -> {
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

			log.info("Words found with findByConcepts(god):");
			log.info("--------------------------------------------");
			words = wordRepo.findByConcepts(god);
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
