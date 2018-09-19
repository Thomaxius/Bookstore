package hh.palvelinohjelmointi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.palvelinohjelmointi.domain.Book;
import hh.palvelinohjelmointi.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository) {
		return (args) -> {
			log.info("save a couple of books");
			repository.save(new Book("Best of Edgar Allan Poe", "Edgar Allan poe", "159494-1", 1891));
			repository.save(new Book("Täällä Pohjantähden Alla", "Väinö Linna","159495-1", 1955));	
			
			log.info("fetching all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
