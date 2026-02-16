package org.example.players;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "players")
public class Player {
    @Id
    private UUID id;
    private String nickname;
    private String role;
    private int age;
    private double salary;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private TeamReference team;

    public Player() {}

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
    public TeamReference getTeam() { return team; }
    public void setTeam(TeamReference team) { this.team = team; }
}