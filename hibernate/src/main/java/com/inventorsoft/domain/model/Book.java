package com.inventorsoft.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@ToString(of = "id")
@Entity
@Table(name = "BOOKS")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    Integer id;

    @Column(name = "TITLE", nullable = false)
    String title;

    @ManyToOne
    Author author;

    @Column(name = "PUBLISHED", nullable = false)
    LocalDate published;

    @Column(name = "STOCK", nullable = false)
    Integer stock = 0;

}
