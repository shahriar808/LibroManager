package com.shahriar.Library.service;

import com.shahriar.Library.entity.Author;
import com.shahriar.Library.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }
    public Author findAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(()-> new RuntimeException("Author not found"));
    }
    public void saveAuthor(Author author){
        authorRepository.save(author);
    }
    public void deleteAuthor(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not found"));
        authorRepository.deleteById(author.getId());
    }
}
