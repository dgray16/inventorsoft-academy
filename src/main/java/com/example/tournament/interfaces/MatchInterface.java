package com.example.tournament.interfaces;


import com.example.tournament.entity.Match;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface MatchInterface {
    void createMatch(Match match);

    Match getMatchById(Long id);

    List<Match> getAllMatch();

    ResponseEntity<String> deleteMatchById(Long id);

    ResponseEntity<String> updateMatch(Match match);
}
