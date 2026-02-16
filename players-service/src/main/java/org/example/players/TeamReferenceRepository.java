package org.example.players;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface TeamReferenceRepository extends JpaRepository<TeamReference, UUID> {
}
