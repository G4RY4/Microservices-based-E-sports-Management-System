package org.example.esportapp;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    public Optional<Team> findById(UUID id) {
        return teamRepository.findById(id);
    }

    public void save(Team team) {
        teamRepository.save(team);
    }

    public void delete(Team team) {
        teamRepository.delete(team);
    }
}
