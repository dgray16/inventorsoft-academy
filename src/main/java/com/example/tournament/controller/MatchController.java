package com.example.tournament.controller;

import com.example.tournament.entity.Match;
import com.example.tournament.entity.Team;
import com.example.tournament.repository.MatchRepository;
import com.example.tournament.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {
    MatchRepository matchRepository;
    MatchService matchService;

    @Autowired
    public MatchController(MatchRepository matchRepository, MatchService matchService) {
        this.matchRepository = matchRepository;
        this.matchService = matchService;
    }

    @PostMapping("/")
    public void createMatch(@Valid @RequestBody Match match) {

        matchService.createMatch(match);
    }

    @GetMapping("/")
    public Match getmatchById(@RequestParam(name = "id") Long id) {

        return matchService.getMatchById(id);
    }


    @GetMapping("/all")
    public List<Match> getAllMatch() {

        return matchService.getAllMatch();
    }

    @PutMapping("/")
    public void updateTeam(@RequestBody Match match) {
        matchService.updateMatch(match);
    }
}
