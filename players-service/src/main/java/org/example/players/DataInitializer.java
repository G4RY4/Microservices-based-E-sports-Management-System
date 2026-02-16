package org.example.players;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {
    private final PlayerService playerService;
    private final TeamReferenceRepository teamRepo;

    public DataInitializer(PlayerService playerService, TeamReferenceRepository teamRepo) {
        this.playerService = playerService;
        this.teamRepo = teamRepo;
    }

    @Override
    public void run(String... args) {

        UUID g2Id = UUID.fromString("07ac0630-93f2-4acd-bbb2-d2e29cfa062a");
        UUID t1Id = UUID.fromString("6af0b5cb-dee2-44f9-9adb-bbd42c41d90c");

        TeamReference g2 = new TeamReference(g2Id, "G2 Esports");
        TeamReference t1 = new TeamReference(t1Id, "T1");
        teamRepo.save(g2);
        teamRepo.save(t1);

        Player caps = new Player();
        caps.setNickname("Caps");
        caps.setRole("Mid");
        caps.setAge(24);
        caps.setSalary(500000);
        playerService.save(caps, g2Id);

        Player hansSama = new Player();
        hansSama.setNickname("Hans Sama");
        hansSama.setRole("ADC");
        hansSama.setAge(25);
        hansSama.setSalary(450000);
        playerService.save(hansSama, g2Id);

        Player faker = new Player();
        faker.setNickname("Faker");
        faker.setRole("Mid");
        faker.setAge(27);
        faker.setSalary(1000000);
        playerService.save(faker, t1Id);
    }
}
