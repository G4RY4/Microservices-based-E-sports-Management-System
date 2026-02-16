package org.example.teams;

import java.util.UUID;

public class TeamListDTO {
    private UUID id;
    private String name;

    public TeamListDTO() {}
    public TeamListDTO(UUID id, String name) {
        this.id = id; this.name = name;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
