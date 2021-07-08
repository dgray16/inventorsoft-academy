package com.example.tournament.service;

import com.example.tournament.entity.Team;
import com.example.tournament.exception.ResourceNotFoundException;
import com.example.tournament.interfaces.TeamInterface;
import com.example.tournament.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService implements TeamInterface {
    private TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) { this.teamRepository = teamRepository;
    }

    @Override
    public void createTeam(Team team) {
        teamRepository.save(team);
    }

    @Override
    public Team getTeamById(Long id) {
        return teamRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Team", "id", id));
    }

    @Override
    public List<Team> getAllTeam() {
        return teamRepository.findAll();
    }

    @Override
    public ResponseEntity<String> deleteTeamById(Long id) {
        String message = "Team was successfully deleted";
        Team team = getTeamById(id);
        teamRepository.delete(team);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @Override
    public ResponseEntity<String> updateTeam(Team teamRequest) {
        String message = "Team was successfully updated";
        Team team = getTeamById(teamRequest.getId());
        team.setNameTeam(teamRequest.getNameTeam());
        team.setCoach(teamRequest.getCoach());
        teamRepository.save(team);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
