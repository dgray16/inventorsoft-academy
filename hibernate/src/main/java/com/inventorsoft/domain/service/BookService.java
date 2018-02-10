package com.inventorsoft.domain.service;

import com.inventorsoft.domain.model.Author;
import com.inventorsoft.domain.model.Book;
import com.inventorsoft.domain.repository.BookRepository;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookService extends GeneralService<Book, Integer> {

    BookRepository bookRepository;

    @Autowired
    AuthorService authorService;

    BookService(BookRepository repository) {
        super(repository);
        this.bookRepository = repository;
    }

    @Transactional
    public Book createTestBook(Author author) {
        Book book = new Book();
        book.setTitle("Code Complete");
        book.setStock(10);
        book.setPublished(LocalDate.now());
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    public Book createTestBook(@NonNull Integer authorId) {
        return createTestBook(authorService.getOne(authorId));
    }

    public List<Book> getBooksByAuthor(Author author) {
        return bookRepository.getAllByAuthor(author);
    }

    /**
     * We assume that young writers are anybody who was born since year 1990.
     */
    public List<Book> getBooksOfYoungWriters() {
        return bookRepository.getBooksOfYoungWriters(LocalDate.ofYearDay(1990, 1));
    }
}
