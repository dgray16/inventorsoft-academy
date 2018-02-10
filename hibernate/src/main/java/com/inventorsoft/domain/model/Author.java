package com.inventorsoft.domain.model;

import com.inventorsoft.domain.converter.NationalityConverter;
import com.inventorsoft.domain.dictionary.Nationality;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString(of = "id")
@Table(name = "AUTHORS")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    Integer id;

    @Column(name = "FIRSTNAME", nullable = false)
    String firstName;

    @Column(name = "LASTNAME", nullable = false)
    String lastName;

    @Column(name = "BIRTHDAY", nullable = false)
    LocalDate birthday;

    @Enumerated
    /*@Convert(converter = NationalityConverter.class)*/
    @Column(name = "NATIONALITY", nullable = false)
    Nationality nationality;

}
