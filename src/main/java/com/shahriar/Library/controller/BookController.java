package com.shahriar.Library.controller;


import com.shahriar.Library.entity.Book;
import com.shahriar.Library.service.AuthorService;
import com.shahriar.Library.service.BookService;
import com.shahriar.Library.service.CategoryService;
import com.shahriar.Library.service.PublisherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final PublisherService publisherService;

    public BookController(BookService bookService, AuthorService authorService, CategoryService categoryService, PublisherService publisherService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.publisherService = publisherService;
    }

    @GetMapping("/books")
    public String findAllBooks(Model model) {
        List<Book> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        return "books";
    }
    @GetMapping("/book/{id}")
    public String findBookById(@PathVariable Long id, Model model) {
        Book book = bookService.findBookById(id);
        model.addAttribute("book", book);
        return "list-book";
    }
    @GetMapping("/books/add")
    public String addBook(Book book, Model model) {
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("publishers", publisherService.findAllPublishers());
        model.addAttribute("authors", authorService.findAllAuthors());
        return "add-book";
    }
    @PostMapping("/books/save")
    public String saveBook(Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-book";
        }
        bookService.saveBook(book);
        model.addAttribute("books", bookService.findAllBooks());
        return "books";
    }
    @GetMapping("/books/update/{id}")
    public String editBook(@PathVariable Long id, Model model) {
        Book book = bookService.findBookById(id);
        model.addAttribute("book", book);
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("publishers", publisherService.findAllPublishers());
        model.addAttribute("authors", authorService.findAllAuthors());
        return "update-book";
    }
    @PostMapping("/books/save-update/{id}")
    public String saveUpdateBook(@PathVariable Long id, Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "update-book";
        }
        bookService.saveBook(book);
        model.addAttribute("books", bookService.findAllBooks());
        return "list-book";
    }
    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id, Model model) {
        bookService.deleteBook(id);
        model.addAttribute("books", bookService.findAllBooks());
        return "books";
    }
}
