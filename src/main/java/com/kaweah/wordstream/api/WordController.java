package com.kaweah.wordstream.api;

import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kaweah.wordstream.WordstreamApplication;
import com.kaweah.wordstream.model.Concept;
import com.kaweah.wordstream.model.Word;
import com.kaweah.wordstream.repository.WordRepository;

@RestController
public class WordController {

	private static final Logger log = LoggerFactory.getLogger(WordstreamApplication.class);

	@Autowired
    private WordRepository wordRepo;

	private static final String template = "{%n  %s: %s%n}";

	@GetMapping("/word")
	public String word(@RequestParam(value = "text") String text) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{\n");
		wordRepo.findByText(text).forEach(word -> {
			log.info(word.toString());
			buffer.append("id       : " + word.getId() + ",\n");
			buffer.append("text     : " + word.getText() + ",\n");
			buffer.append("language : " + word.getLanguage().getName() + ",\n");
			buffer.append("concept : [");
			java.util.Iterator <Concept> concepts = word.getConcepts().iterator();
			for (int i = 0; i < word.getConcepts().size(); i++) {
				buffer.append(concepts.next().getDescription());
				if (i < (word.getConcepts().size() - 1)) {
					buffer.append(",\n");
				}
			}
			buffer.append("]\n");
		});		
		buffer.append("}\n");
		return buffer.toString();
	}

}
