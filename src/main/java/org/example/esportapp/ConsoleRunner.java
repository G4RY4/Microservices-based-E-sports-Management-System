package org.example.esportapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Component
@Order(2)
public class ConsoleRunner implements CommandLineRunner {

    private final TeamService teamService;
    private final PlayerService playerService;

    public ConsoleRunner(TeamService teamService, PlayerService playerService) {
        this.teamService = teamService;
        this.playerService = playerService;
    }

    @Override
    public void run(String... args) {
        System.out.println("=== Konsolowa aplikacja esportowa ===");
        printCommands();
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                System.out.print("\nPodaj komendę: ");
                if (!scanner.hasNextLine()) break;
                String command = scanner.nextLine().trim().toLowerCase();

                switch (command) {
                    case "help" -> printCommands();

                    case "teams" -> {
                        System.out.println("Drużyny w bazie:");
                        teamService.findAll().forEach(System.out::println);
                    }

                    case "players" -> {
                        System.out.println("Zawodnicy w bazie:");
                        playerService.findAll().forEach(System.out::println);
                    }

                    case "add" -> {
                        System.out.print("Nickname: ");
                        String nickname = scanner.nextLine();
                        System.out.print("Rola: ");
                        String role = scanner.nextLine();
                        System.out.print("Wiek: ");
                        int age = Integer.parseInt(scanner.nextLine());
                        System.out.print("Wynagrodzenie: ");
                        double salary = Double.parseDouble(scanner.nextLine());

                        List<Team> teams = teamService.findAll();
                        if (teams.isEmpty()) {
                            System.out.println("Brak drużyn — dodaj team najpierw komendą addteam.");
                            break;
                        }

                        System.out.println("Wybierz drużynę (numer):");
                        for (int i = 0; i < teams.size(); i++) {
                            System.out.println(i + ": " + teams.get(i));
                        }
                        int index = Integer.parseInt(scanner.nextLine());
                        Team chosen = teams.get(index);

                        // Użyj buildera lub setterów (jeśli Lombok jeszcze nie działa)
                        Player newPlayer = Player
                                .builder()
                                .id(UUID.randomUUID())
                                .nickname(nickname)
                                .role(role)
                                .age(age)
                                .salary(salary)
                                .team(chosen)
                                .build()
                                .linkTeam();

                        playerService.save(newPlayer);
                        System.out.println("Dodano zawodnika: " + newPlayer);
                    }

                    case "addteam" -> {
                        System.out.print("Nazwa: ");
                        String name = scanner.nextLine();
                        System.out.print("Region: ");
                        String region = scanner.nextLine();
                        System.out.print("Rok: ");
                        int year = Integer.parseInt(scanner.nextLine());

                        Team team = Team.builder()
                                .id(UUID.randomUUID())
                                .name(name)
                                .region(region)
                                .foundedYear(year)
                                .build();
                        teamService.save(team);
                        System.out.println("Dodano drużynę: " + team);
                    }

                    case "delete" -> {
                        List<Player> players = playerService.findAll();
                        if (players.isEmpty()) {
                            System.out.println("Brak zawodników do usunięcia.");
                            break;
                        }
                        for (int i = 0; i < players.size(); i++) {
                            System.out.println(i + ": " + players.get(i));
                        }
                        int idx = Integer.parseInt(scanner.nextLine());
                        Player toDelete = players.get(idx);
                        playerService.delete(toDelete);
                        System.out.println("Usunięto: " + toDelete);
                    }

                    case "exit" -> {
                        System.out.println("Koniec programu.");
                        running = false;
                    }

                    default -> System.out.println("Nieznana komenda. Wpisz 'help'.");
                }
            }
        }
    }

    private void printCommands() {
        System.out.println("Dostępne komendy:");
        System.out.println("help — lista komend");
        System.out.println("teams — wypisz drużyny");
        System.out.println("players — wypisz zawodników");
        System.out.println("addteam — dodaj drużynę");
        System.out.println("add — dodaj zawodnika");
        System.out.println("delete — usuń zawodnika");
        System.out.println("exit — zakończ program");
    }
}
