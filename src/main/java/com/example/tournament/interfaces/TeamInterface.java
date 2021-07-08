package com.example.tournament.interfaces;


import com.example.tournament.entity.Team;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface TeamInterface {
    void createTeam(Team team);

    Team getTeamById(Long id);

    List<Team> getAllTeam();

    ResponseEntity<String> deleteTeamById(Long id);

    ResponseEntity<String> updateTeam(Team team);
}
