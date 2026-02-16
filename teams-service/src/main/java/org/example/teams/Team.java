package org.example.teams;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    private UUID id;
    private String name;
    private String region;
    private int foundedYear;

    public Team() {}

    public Team(UUID id, String name, String region, int foundedYear) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.foundedYear = foundedYear;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }
    public int getFoundedYear() { return foundedYear; }
    public void setFoundedYear(int foundedYear) { this.foundedYear = foundedYear; }
}
