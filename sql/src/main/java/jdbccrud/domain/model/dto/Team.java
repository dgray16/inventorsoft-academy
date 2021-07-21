package com.homework.jdbccrud.domain.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Team {
    String name;
    String capitan;
    String coach;

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", capitan='" + capitan + '\'' +
                ", coach='" + coach + '\'' +
                '}';
    }
}
