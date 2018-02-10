package com.inventorsoft.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString(of = "id")
@Table(name = "BORROWINGS")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    /*@ManyToOne*/
    Book book;

    @ManyToOne
    Member member;

    @Column(name = "BORROWDATE", nullable = false)
    LocalDate borrowDate;

    @Column(name = "RETURNDATE", nullable = false)
    LocalDate returnDate;

}

