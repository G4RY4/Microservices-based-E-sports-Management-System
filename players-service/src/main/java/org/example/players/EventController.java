package org.example.players;

import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final PlayerService service;

    public EventController(PlayerService service) { this.service = service; }

    @PostMapping
    public void handleEvent(@RequestBody Map<String, String> event) {
        String type = event.get("eventType");
        UUID teamId = UUID.fromString(event.get("teamId"));
        String teamName = event.get("teamName");

        if ("TEAM_CREATED".equals(type)) {
            service.handleTeamCreated(teamId, teamName);
        } else if ("TEAM_DELETED".equals(type)) {
            service.handleTeamDeleted(teamId);
        }
    }
}
