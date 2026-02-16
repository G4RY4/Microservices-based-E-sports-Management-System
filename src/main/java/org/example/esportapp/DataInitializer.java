package org.example.esportapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;
import java.util.UUID;

@Component
@Order(1)
public class DataInitializer implements CommandLineRunner {

    private final TeamService teamService;
    private final PlayerService playerService;

    public DataInitializer(TeamService teamService, PlayerService playerService) {
        this.teamService = teamService;
        this.playerService = playerService;
    }

    @Override
    public void run(String... args) throws Exception {
        Team g2 = Team.builder()
                .id(UUID.randomUUID())
                .name("G2 Esports")
                .region("EU")
                .foundedYear(2013)
                .build();

        Team ig = Team.builder()
                .id(UUID.randomUUID())
                .name("Invictus Gaming")
                .region("CN")
                .foundedYear(2011)
                .build();

        Player caps = Player.builder()
                .id(UUID.randomUUID())
                .nickname("Caps")
                .role("Mid")
                .age(24)
                .salary(230_000)
                .team(g2)
                .build()
                .linkTeam();

        Player hans = Player.builder()
                .id(UUID.randomUUID())
                .nickname("Hans Sama")
                .role("ADC")
                .age(25)
                .salary(210_000)
                .team(g2)
                .build()
                .linkTeam();

        Player gala = Player.builder()
                .id(UUID.randomUUID())
                .nickname("Gala")
                .role("ADC")
                .age(23)
                .salary(220_000)
                .team(ig)
                .build()
                .linkTeam();

        teamService.save(g2);
        teamService.save(ig);

        playerService.save(caps);
        playerService.save(hans);
        playerService.save(gala);

        System.out.println("Drużyny w bazie:");
        teamService.findAll().forEach(System.out::println);

        System.out.println("Zawodnicy w bazie:");
        playerService.findAll().forEach(System.out::println);

        System.out.println("Zawodnicy G2:");
        playerService.findByTeam(g2).forEach(System.out::println);
    }
}
