package org.example.players;

import java.util.UUID;

public class PlayerListDTO {
    private UUID id;
    private String nickname;
    private String role;

    public PlayerListDTO() {}
    public PlayerListDTO(UUID id, String nickname, String role) {
        this.id = id; this.nickname = nickname; this.role = role;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
