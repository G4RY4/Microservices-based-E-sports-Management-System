package org.example.players;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "team_references")
public class TeamReference {
    @Id
    private UUID id;
    private String name;

    public TeamReference() {}

    public TeamReference(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
