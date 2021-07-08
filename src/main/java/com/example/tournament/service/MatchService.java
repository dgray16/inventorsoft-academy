package com.example.tournament.service;

import com.example.tournament.entity.Match;
import com.example.tournament.entity.Team;
import com.example.tournament.exception.ResourceNotFoundException;
import com.example.tournament.interfaces.MatchInterface;
import com.example.tournament.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class MatchService implements MatchInterface {

    private MatchRepository matchRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public void createMatch(Match matchRequest) {
        matchRepository.save(matchRequest);
    }

    @Override
    public Match getMatchById(Long id) {
        return matchRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Match", "id", id));
    }

    @Override
    public List<Match> getAllMatch() {
        return matchRepository.findAll();
    }

    @Override
    public ResponseEntity<String> deleteMatchById(Long id) {
        String message = "Match was successfully deleted";
        Match match = matchRepository.getById(id);
        matchRepository.delete(match);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @Override
    public ResponseEntity<String> updateMatch(Match matchRequest) {
        String message = "Match was successfully updated";
        Match match = matchRepository.getById(matchRequest.getId());
        match.setTeam1(matchRequest.getTeam1());
        match.setTeam2(matchRequest.getTeam2());
        match.setScore(matchRequest.getScore());
        matchRepository.save(match);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
