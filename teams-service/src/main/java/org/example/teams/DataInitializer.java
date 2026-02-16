package org.example.teams;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {
    private final TeamService teamService;

    public DataInitializer(TeamService teamService) {
        this.teamService = teamService;
    }

    @Override
    public void run(String... args) {
        Team g2 = new Team(UUID.randomUUID(), "G2 Esports", "Europe", 2013);
        Team t1 = new Team(UUID.randomUUID(), "T1", "Korea", 2003);

        teamService.save(g2);
        teamService.save(t1);
    }
}
