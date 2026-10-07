package tn.esprit.autoloc;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;

import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class AgenceTests {

    @Autowired
    private AgenceRepositoryMock agenceRepository;

    @Test
    void addAgence() {
        Agence agence = new Agence();
        agence.setNom("Agence ariana");
        agence.setAdresse("1 Rue Hedi");
        agence.setTelephone("71585874");
        agence.setVille("Tunis");

        Vehicule v1 = new Vehicule();
        v1.setImmatriculation("785414TU96");
        v1.setMarque("Isuzu");
        v1.setModele("DMax");
        v1.setCategorie(CategorieVehicule.SUV);
        v1.setStatut(StatutVehicule.Maintenance);
        v1.setTarifJournalier(new BigDecimal("100"));
        v1.setAgence(agence);

        Vehicule v2 = new Vehicule();
        v2.setImmatriculation("785414TU95");
        v2.setMarque("Toyota");
        v2.setModele("Yaris");
        v2.setCategorie(CategorieVehicule.UTILITAIRE);
        v2.setStatut(StatutVehicule.DISPONIBLE);
        v2.setTarifJournalier(new BigDecimal("80"));
        v2.setAgence(agence);

        Set<Vehicule> vehicules = new HashSet<>();
        vehicules.add(v1);
        vehicules.add(v2);
        agence.setVehicules(vehicules);

        agenceRepository.save(agence);

        assertNotNull(agence.getIdAgence());
    }
    @Test
    void loadAgence() {
        Iterable<Agence> agences = agenceRepository.findAll();   // Q12

        StringBuilder sb = new StringBuilder();                  // Q13
        for (Agence a : agences) {
            sb.append("Agence ID : ").append(a.getIdAgence()).append("\n");
            sb.append("Nom : ").append(a.getNom()).append("\n");
            sb.append("Nombre de véhicules : ").append(a.getVehicules().size()).append("\n");

            for (Vehicule v : a.getVehicules()) {
                sb.append("   - Véhicule ID : ").append(v.getIdVehicule())
                        .append(" | Immatriculation : ").append(v.getImmatriculation())
                        .append("\n");
            }
            sb.append("\n");
        }
        fail(sb.toString());
    }

}

interface AgenceRepositoryMock extends CrudRepository<Agence, Long> {
}