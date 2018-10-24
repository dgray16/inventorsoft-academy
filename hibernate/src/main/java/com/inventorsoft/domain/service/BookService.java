package com.inventorsoft.domain.service;

import com.inventorsoft.domain.model.Author;
import com.inventorsoft.domain.model.Book;
import com.inventorsoft.domain.repository.BookRepository;
import com.inventorsoft.domain.service.base.GeneralService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookService extends GeneralService<Book, Integer> {

    BookRepository bookRepository;

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

    @Transactional(readOnly = true)
    public List<Book> getBooksByAuthor(Author author) {
        List<Book> allByAuthor = bookRepository.getAllByAuthor(author);

        /* Right here I can show additional query when call Author */
        System.out.println("");

        return allByAuthor;
    }

    /**
     * We assume that young writer is anybody who was born since year 1990.
     */
    @Transactional(readOnly = true)
    public List<Book> getBooksOfYoungWriters() {

        /* Pure Spring Data JPA example */
        /*return bookRepository.getAllByAuthorBirthdayAfter(LocalDate.ofYearDay(1990, NumberUtils.INTEGER_ONE));*/

        /* Manual Query example */
        return bookRepository.getBooksOfYoungWriters(LocalDate.ofYearDay(1990, 1));
    }
}
