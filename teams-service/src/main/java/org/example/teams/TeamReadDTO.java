package org.example.teams;

import java.util.UUID;

public class TeamReadDTO {
    private UUID id;
    private String name;
    private String region;
    private int foundedYear;

    public TeamReadDTO() {}
    public TeamReadDTO(UUID id, String name, String region, int foundedYear) {
        this.id = id; this.name = name; this.region = region; this.foundedYear = foundedYear;
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
