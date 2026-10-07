package tn.esprit.autoloc.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContrat;

    private LocalDate dateSignature;

    private BigDecimal montantTotal;

    private boolean valide;

    // Q18 : le chargement du contrat implique le chargement des paiements
    @OneToMany(mappedBy = "contrat", fetch = FetchType.EAGER)
    private Set<Paiement> paiements = new HashSet<>();
    @OneToOne(mappedBy = "contrat")
    private Reservation reservation;

    public Contrat() {
    }

    public Contrat(LocalDate dateSignature, BigDecimal montantTotal, boolean valide) {
        this.dateSignature = dateSignature;
        this.montantTotal = montantTotal;
        this.valide = valide;
    }

    public Long getIdContrat() { return idContrat; }
    public void setIdContrat(Long idContrat) { this.idContrat = idContrat; }

    public LocalDate getDateSignature() { return dateSignature; }
    public void setDateSignature(LocalDate dateSignature) { this.dateSignature = dateSignature; }

    public BigDecimal getMontantTotal() { return montantTotal; }
    public void setMontantTotal(BigDecimal montantTotal) { this.montantTotal = montantTotal; }

    public boolean isValide() { return valide; }
    public void setValide(boolean valide) { this.valide = valide; }

    public Set<Paiement> getPaiements() { return paiements; }
    public void setPaiements(Set<Paiement> paiements) { this.paiements = paiements; }
}