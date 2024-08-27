package com.shahriar.Library;

import com.shahriar.Library.entity.Author;
import com.shahriar.Library.entity.Book;
import com.shahriar.Library.entity.Category;
import com.shahriar.Library.entity.Publisher;
import com.shahriar.Library.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	@Bean
	public CommandLineRunner initialCreate(BookService bookService) {
		return (args) -> {
			Book book = new Book("ABC Book", "ABC Book", "ABC Book");
			Author author = new Author("John Doe", "John Doe");
			Category category = new Category("Java");
			Publisher publisher = new Publisher("JAVA Publisher");
			book.addAuthor(author);
			book.addCategory(category);
			book.addPublisher(publisher);
			bookService.saveBook(book);

			Book book2 = new Book("ABC Book2", "ABC Book2", "ABC Book2");
			Author author2 = new Author("John Doe2", "John Doe2");
			Category category2 = new Category("Java2");
			Publisher publisher2 = new Publisher("JAVA Publisher2");
			book2.addAuthor(author2);
			book2.addCategory(category2);
			book2.addPublisher(publisher2);
			bookService.saveBook(book2);

			Book book3 = new Book("ABC Book3", "ABC Book3", "ABC Book3");
			Author author3 = new Author("John Doe3", "John Doe3");
			Category category3 = new Category("Java3");
			Publisher publisher3 = new Publisher("JAVA Publisher3");
			book3.addAuthor(author3);
			book3.addCategory(category3);
			book3.addPublisher(publisher3);
			bookService.saveBook(book3);
		};
	}

}
