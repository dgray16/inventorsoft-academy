package com.inventorsoft.domain.service;

import com.inventorsoft.domain.model.Book;
import com.inventorsoft.domain.model.Borrowing;
import com.inventorsoft.domain.model.Member;
import com.inventorsoft.domain.repository.BorrowingRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BorrowingService extends GeneralService<Borrowing, Integer> {

    BorrowingRepository borrowingRepository;

    BorrowingService(BorrowingRepository repository) {
        super(repository);
        this.borrowingRepository = repository;
    }

    @Transactional
    public Borrowing createTestBorrowing(Book book, Member member) {
        Borrowing borrowing = new Borrowing();
        borrowing.setBook(book);
        borrowing.setMember(member);
        borrowing.setBorrowDate(LocalDate.ofYearDay(2018, 1));
        borrowing.setReturnDate(LocalDate.ofYearDay(2018, 20));
        return borrowingRepository.save(borrowing);
    }
}
