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
import javax.persistence.Table;

@Getter
@Setter
@ToString(of = "id")
@Entity
@Table(name = "MEMBERS")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    Integer id;

    @Column(name = "FIRSTNAME", nullable = false)
    String firstName;

    @Column(name = "LASTNAME", nullable = false)
    String lastName;

}
