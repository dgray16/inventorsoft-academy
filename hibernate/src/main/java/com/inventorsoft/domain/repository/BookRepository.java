package com.inventorsoft.domain.repository;

import com.inventorsoft.domain.model.Author;
import com.inventorsoft.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> getAllByAuthor(Author author);

    /* Manual query with parameter */
    @Query(value = """
        select b from Book b
            inner join b.author a
        where a.birthday > :date
    """)
    List<Book> getBooksOfYoungWriters(@Param("date") LocalDate date);

    /* Pure Spring Data JPA */
    /*List<Book> getAllByAuthorBirthdayAfter(LocalDate date);*/

}
