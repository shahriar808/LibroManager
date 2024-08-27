package com.shahriar.Library.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "isbn", nullable = false, length = 50, unique = true)
    private String isbn;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description", nullable = false, length = 250)
    private String description;

    public Book(String description, String name, String isbn) {
        this.description = description;
        this.name = name;
        this.isbn = isbn;
    }

    @ManyToMany(cascade = {CascadeType.ALL} )
    @JoinTable(name = "books_authors",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")})
    private Set<Author> authors = new HashSet<Author>();

    @ManyToMany(cascade = {CascadeType.ALL} )
    @JoinTable(name = "books_categories",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private Set<Category> categories = new HashSet<Category>();

    @ManyToMany(cascade = {CascadeType.ALL} )
    @JoinTable(name = "books_publishers",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "publisher_id")})
    private Set<Publisher> publishers = new HashSet<Publisher>();

    public void  removePublisher(Publisher publisher) {
        this.publishers.remove(publisher);
        publisher.getBooks().remove(publisher);
    }
    public void addPublisher(Publisher publisher){
        this.publishers.add(publisher);
        publisher.getBooks().add(this);
    }

    public void  removeAuthor(Author author) {
        this.authors.remove(author);
        author.getBooks().remove(author);
    }
    public void addAuthor(Author author){
        this.authors.add(author);
        author.getBooks().add(this);
    }
    public void  removeCategory(Category category) {
        this.categories.remove(category);
        category.getBooks().remove(this);
    }
    public void addCategory(Category category){
        this.categories.add(category);
        category.getBooks().add(this);
    }
}
