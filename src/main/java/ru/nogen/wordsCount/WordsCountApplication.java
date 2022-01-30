package ru.nogen.wordsCount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.nogen.wordsCount.model.Page;

@SpringBootApplication
public class WordsCountApplication {

	public static void main(String[] args) {
		SpringApplication.run(WordsCountApplication.class, args);
	}

}
