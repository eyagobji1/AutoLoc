package tn.esprit.autoloc;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.domain.Agence;

public interface AgenceRepository extends JpaRepository<Agence, Long> {
}
