package org.example.esportapp;

import lombok.*;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString(exclude = "players")
@EqualsAndHashCode(exclude = "players")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teams")
public class Team implements Comparable<Team>, Serializable {

    @Id
    private UUID id;

    private String name;
    private String region;
    private int foundedYear;

    @Builder.Default
    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> players = new ArrayList<>();

    public void addPlayer(Player player) {
        if (player == null) return;
        if (!players.contains(player)) {
            players.add(player);
            player.setTeam(this);
        }
    }

    public void removePlayer(Player player) {
        if (player == null) return;
        players.remove(player);
        if (player.getTeam() == this) {
            player.setTeam(null);
        }
    }

    @Override
    public int compareTo(Team o) {
        int r = this.region.compareTo(o.region);
        if (r != 0) return r;
        int n = this.name.compareTo(o.name);
        if (n != 0) return n;
        return Integer.compare(this.foundedYear, o.foundedYear);
    }
}
