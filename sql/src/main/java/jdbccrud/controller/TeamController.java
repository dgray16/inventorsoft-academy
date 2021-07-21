package com.homework.jdbccrud.controller;


import com.homework.jdbccrud.domain.model.dto.MatchDto;
import com.homework.jdbccrud.domain.service.TeamService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
@RequiredArgsConstructor
@RequestMapping("/teams")
public class TeamController {
    TeamService teamService;

    @GetMapping
    public ResponseEntity<List<MatchDto>> getAllUsers() {
        return ResponseEntity.ok(teamService.getAll());
    }
}
