package com.inventorsoft.domain.repository;

import com.inventorsoft.domain.model.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Integer> {

}
