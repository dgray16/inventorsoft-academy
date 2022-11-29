package com.inventorsoft.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString(of = "id")
@Table(name = "borrowings")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    Book book;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    Member member;

    @Column(nullable = false)
    LocalDate borrowDate;

    @Column(nullable = false)
    LocalDate returnDate;

}

