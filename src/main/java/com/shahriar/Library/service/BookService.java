package com.shahriar.Library.service;

import com.shahriar.Library.entity.Book;
import com.shahriar.Library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }
    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(()-> new RuntimeException("Book not found"));
    }
    public void saveBook(Book book) {
        bookRepository.save(book);
    }
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new RuntimeException("Book not found"));
        bookRepository.deleteById(book.getId());
    }
}
