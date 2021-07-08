package com.example.tournament.controller;

import com.example.tournament.entity.Team;
import com.example.tournament.repository.TeamRepository;
import com.example.tournament.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {
    TeamRepository teamRepository;
    TeamService teamService;

    @Autowired
    public TeamController(TeamRepository teamRepository, TeamService teamService) {
        this.teamRepository = teamRepository;
        this.teamService = teamService;
    }

    @PostMapping("/")
    public void createTeam(@Valid @RequestBody Team team) {
        teamService.createTeam(team);
    }

    @GetMapping("/")
    public Team getTeamById(@RequestParam(name = "id") Long id) {
        return teamService.getTeamById(id);
    }

    @GetMapping("/all")
    public List<Team> getAllTeam() {
        return teamService.getAllTeam();
    }

    @DeleteMapping("/")
    public void deleteTeam(@RequestParam(name = "id") Long id) {
        teamService.deleteTeamById(id);
    }

    @PutMapping("/")
    public void updateTeam(@RequestBody Team team) {
        teamService.updateTeam(team);
    }

}
