package com.inventorsoft.author;

import com.inventorsoft.domain.model.Author;
import com.inventorsoft.domain.model.Book;
import com.inventorsoft.domain.model.Borrowing;
import com.inventorsoft.domain.model.Member;
import com.inventorsoft.domain.service.AuthorService;
import com.inventorsoft.domain.service.BookService;
import com.inventorsoft.domain.service.BorrowingService;
import com.inventorsoft.domain.service.MemberService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorController {

    AuthorService authorService;
    BookService bookService;
    MemberService memberService;
    BorrowingService borrowingService;

    @GetMapping(value = "/authors")
    public ResponseEntity<List<Author>> authors() {
        Author author = authorService.createTestAuthor();
        Book book = bookService.createTestBook(author);
        Member member = memberService.createTestMember();
        Borrowing borrowing = borrowingService.createTestBorrowing(book, member);

        List<Book> booksByAuthor = bookService.getBooksByAuthor(author);

        /* With this collection of Books I can demonstrate LazyInitializationException */
        List<Book> booksOfYoungAuthors = bookService.getBooksOfYoungWriters();

        return ResponseEntity.ok(authorService.getAll());
    }

}
