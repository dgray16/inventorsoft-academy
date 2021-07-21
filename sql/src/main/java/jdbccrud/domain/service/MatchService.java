package com.homework.jdbccrud.domain.service;

import com.homework.jdbccrud.domain.model.dto.MatchDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MatchService {
    @Transactional(readOnly = true)
    public List<MatchDto> getAll() {
        return null;
    }
}
