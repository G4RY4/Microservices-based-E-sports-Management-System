package org.example.teams;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    private final TeamService service;

    public TeamController(TeamService service) { this.service = service; }

    @GetMapping
    public List<Team> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> get(@PathVariable UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Team> create(@RequestBody Team team) {
        Team saved = service.save(team);
        return ResponseEntity.created(URI.create("/api/teams/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> update(@PathVariable UUID id, @RequestBody Team team) {
        return service.findById(id).map(existing -> {
            if (team.getName() != null) {
                existing.setName(team.getName());
            }
            if (team.getRegion() != null) {
                existing.setRegion(team.getRegion());
            }
            if (team.getFoundedYear() != 0) {
                existing.setFoundedYear(team.getFoundedYear());
            }

            service.save(existing);
            return ResponseEntity.ok(existing);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
