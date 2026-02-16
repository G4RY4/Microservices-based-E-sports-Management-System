package org.example.esportapp;

import lombok.*;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@ToString(exclude = "team")
@EqualsAndHashCode(exclude = "team")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "players")
public class Player implements Comparable<Player>, Serializable {

    @Id
    private UUID id;

    private String nickname;
    private String role;
    private int age;
    private double salary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Player linkTeam() {
        if (team != null) team.addPlayer(this);
        return this;
    }

    @Override
    public int compareTo(Player other) {
        int byNick = this.nickname.compareTo(other.nickname);
        if (byNick != 0) return byNick;
        int byAge = Integer.compare(this.age, other.age);
        if (byAge != 0) return byAge;
        return Double.compare(this.salary, other.salary);
    }
}
