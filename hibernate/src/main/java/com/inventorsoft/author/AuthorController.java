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
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @Autowired
    BookService bookService;

    @Autowired
    MemberService memberService;

    @Autowired
    BorrowingService borrowingService;

    @ResponseBody
    @RequestMapping(value = "/authors", method = RequestMethod.GET)
    public List<Author> authors() {
        Author author = authorService.createTestAuthor();
        Book book = bookService.createTestBook(author.getId());
        Member member = memberService.createTestMember();
        Borrowing borrowing = borrowingService.createTestBorrowing(book, member);

        List<Book> booksByAuthor = bookService.getBooksByAuthor(author);
        List<Book> booksOfYoungAuthors = bookService.getBooksOfYoungWriters();

        return authorService.getAll();
    }

}
