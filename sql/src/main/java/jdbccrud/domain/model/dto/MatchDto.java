package com.homework.jdbccrud.domain.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;


@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MatchDto {
    int round;
    int numberOfRounds = 3;
    String score;

    @Override
    public String toString() {
        return "MatchDto{" +
                "round=" + round +
                ", numberOfRounds=" + numberOfRounds +
                ", score='" + score + '\'' +
                '}';
    }
}
