package org.example.players;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class PlayerService {
    private final PlayerRepository playerRepo;
    private final TeamReferenceRepository teamRepo;

    public PlayerService(PlayerRepository playerRepo, TeamReferenceRepository teamRepo) {
        this.playerRepo = playerRepo;
        this.teamRepo = teamRepo;
    }

    public List<Player> findAll() { return playerRepo.findAll(); }
    public List<Player> findAllByTeamId(UUID teamId) { return playerRepo.findByTeam_Id(teamId); }
    public Optional<Player> findById(UUID id) { return playerRepo.findById(id); }

    public Player save(Player player, UUID teamId) {
        TeamReference team = teamRepo.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team not found"));
        if (player.getId() == null) player.setId(UUID.randomUUID());
        player.setTeam(team);
        return playerRepo.save(player);
    }

    public Player update(UUID id, Player p) {
        return playerRepo.findById(id).map(existing -> {
            if(p.getNickname() != null) existing.setNickname(p.getNickname());
            if(p.getRole() != null) existing.setRole(p.getRole());
            if(p.getAge() > 0) existing.setAge(p.getAge());
            if(p.getSalary() > 0) existing.setSalary(p.getSalary());
            return playerRepo.save(existing);
        }).orElseThrow(() -> new IllegalArgumentException("Player not found"));
    }

    public void delete(UUID id) {
        playerRepo.deleteById(id);
    }

    @Transactional
    public void handleTeamDeleted(UUID teamId) {
        teamRepo.findById(teamId).ifPresent(team -> {
            List<Player> players = playerRepo.findByTeam_Id(teamId);
            playerRepo.deleteAll(players);
            teamRepo.delete(team);
        });
    }

    public void handleTeamCreated(UUID teamId, String name) {
        teamRepo.save(new TeamReference(teamId, name));
    }
}
