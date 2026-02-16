package org.example.players;

import java.util.UUID;

public class PlayerReadDTO {
    private UUID id;
    private String nickname;
    private String role;
    private int age;
    private double salary;
    private UUID teamId;
    private String teamName;

    public PlayerReadDTO() {}

    public PlayerReadDTO(UUID id, String nickname, String role, int age, double salary, UUID teamId, String teamName) {
        this.id = id;
        this.nickname = nickname;
        this.role = role;
        this.age = age;
        this.salary = salary;
        this.teamId = teamId;
        this.teamName = teamName;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
    public UUID getTeamId() { return teamId; }
    public void setTeamId(UUID teamId) { this.teamId = teamId; }
    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }
}
