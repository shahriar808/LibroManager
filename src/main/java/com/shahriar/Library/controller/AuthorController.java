package com.shahriar.Library.controller;

import com.shahriar.Library.entity.Author;
import com.shahriar.Library.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthorController {
    private final AuthorService authorService;
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
    @GetMapping("authors")
    public String authors(Model model) {
        model.addAttribute("authors", authorService.findAllAuthors());
        return "authors";
    }
    @GetMapping("/authors/add")
    public String addAuthor(Author author) {
        return "add-author";
    }
    @PostMapping("/authors/save")
    public String saveAuthor(Author author, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "add-author";
        }
        authorService.saveAuthor(author);
        model.addAttribute("authors", authorService.findAllAuthors());
        return "authors";
    }

    @GetMapping("/authors/update/{id}")
    public String updateAuthor(@PathVariable Long id, Model model) {
        model.addAttribute("author", authorService.findAuthorById(id));
        return "update-author";
    }

    @PostMapping("/authors/save-update/{id}")
    public String saveUpdateAuthor(@PathVariable Long id, Author author, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "update-author";
        }
        authorService.saveAuthor(author);
        model.addAttribute("authors", authorService.findAllAuthors());
        return "redirect:/authors";
    }

    @GetMapping("/authors/delete/{id}")
    public String deleteAuthor(@PathVariable Long id, Model model) {
        authorService.deleteAuthor(id);
        model.addAttribute("authors", authorService.findAllAuthors());
        return "authors";
    }
}
