package org.example.players;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class PlayerController {
    private final PlayerService service;

    public PlayerController(PlayerService service) { this.service = service; }

    @GetMapping("/players")
    public List<Player> list() { return service.findAll(); }

    @GetMapping("/players/{id}")
    public ResponseEntity<Player> get(@PathVariable UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/teams/{teamId}/players")
    public ResponseEntity<List<Player>> listByTeam(@PathVariable UUID teamId) {
        return ResponseEntity.ok(service.findAllByTeamId(teamId));
    }

    @PostMapping("/teams/{teamId}/players")
    public ResponseEntity<Player> create(@PathVariable UUID teamId, @RequestBody Player player) {
        try {
            Player saved = service.save(player, teamId);
            return ResponseEntity.created(URI.create("/api/players/" + saved.getId())).body(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/players/{id}")
    public ResponseEntity<Player> update(@PathVariable UUID id, @RequestBody Player player) {
        try {
            return ResponseEntity.ok(service.update(id, player));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/players/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
