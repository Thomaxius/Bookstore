package hh.palvelinohjelmointi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.palvelinohjelmointi.domain.Book;
import hh.palvelinohjelmointi.domain.BookRepository;
import hh.palvelinohjelmointi.domain.Category;
import hh.palvelinohjelmointi.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository, CategoryRepository categoryRepository) {
		return (args) -> {
			log.info("Saving a couple of books and categories..");
			categoryRepository.save(new Category("Kaunokirjallisuus"));
			categoryRepository.save(new Category("Fiktio"));
			categoryRepository.save(new Category("Fantasia"));
			
			bookRepository.save(new Book("Best of Edgar Allan Poe", "Edgar Allan poe", "159494-1", 1891, 
					categoryRepository.findByName("Kaunokirjallisuus").get(0)));
			bookRepository.save(new Book("Täällä Pohjantähden Alla", "Väinö Linna","159495-1", 1955, 
					categoryRepository.findByName("Kaunokirjallisuus").get(0)));	
			
			log.info("Fetching all books..");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
