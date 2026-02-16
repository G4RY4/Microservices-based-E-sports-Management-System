package org.example.teams;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class TeamService {
    private final TeamRepository repository;
    private final RestTemplate restTemplate;

    private final String PLAYERS_SERVICE_URL = "http://localhost:8082/api/events";

    public TeamService(TeamRepository repository) {
        this.repository = repository;
        this.restTemplate = new RestTemplate();
    }

    public List<Team> findAll() { return repository.findAll(); }
    public Optional<Team> findById(UUID id) { return repository.findById(id); }

    public Team save(Team team) {
        boolean isNew = (team.getId() == null) || repository.findById(team.getId()).isEmpty();
        if (team.getId() == null) team.setId(UUID.randomUUID());

        Team saved = repository.save(team);

        if (isNew) notifyPlayersService("TEAM_CREATED", saved);
        return saved;
    }

    public void delete(UUID id) {
        repository.findById(id).ifPresent(team -> {
            repository.delete(team);
            notifyPlayersService("TEAM_DELETED", team);
        });
    }

    private void notifyPlayersService(String type, Team team) {
        try {
            Map<String, String> event = new HashMap<>();
            event.put("eventType", type);
            event.put("teamId", team.getId().toString());
            event.put("teamName", team.getName());
            restTemplate.postForLocation(PLAYERS_SERVICE_URL, event);
        } catch (Exception e) {
            System.err.println("Warning: Players service unreachable");
        }
    }
}
