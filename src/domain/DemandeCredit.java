package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DemandeCredit {

    private final UUID id;

    private final String idClient;

    private final Money montant;

    private final DureeCredit duree;

    private final List<GarantieDocument> garanties;

    private StatutDemande statut;

    public DemandeCredit(
            String idClient,
            Money montant,
            DureeCredit duree,
            List<GarantieDocument> garanties
    ) {

        if (idClient == null || idClient.isBlank()) {

            throw new IllegalArgumentException(
                    "Client invalide"
            );
        }

        if (garanties == null || garanties.size() < 2) {

            throw new IllegalArgumentException(
                    "Minimum 2 garanties"
            );
        }

        this.id = UUID.randomUUID();

        this.idClient = idClient;

        this.montant = montant;

        this.duree = duree;

        this.garanties = new ArrayList<>(garanties);

        this.statut = StatutDemande.NOUVEAU;
    }


    public void analyser() {

        if (statut != StatutDemande.NOUVEAU) {

            throw new IllegalStateException(
                    "Transition invalide"
            );
        }

        statut = StatutDemande.EN_ANALYSE;
    }

    public void valider() {

        if (statut != StatutDemande.EN_ANALYSE) {

            throw new IllegalStateException(
                    "Transition invalide"
            );
        }

        statut = StatutDemande.VALIDE;
    }

    public void rejeter() {

        if (statut != StatutDemande.EN_ANALYSE) {

            throw new IllegalStateException(
                    "Transition invalide"
            );
        }

        statut = StatutDemande.REJETE;
    }


    public List<GarantieDocument> garanties() {
        return List.copyOf(garanties);
    }

    public StatutDemande statut() {
        return statut;
    }

    public Money montant() {
        return montant;
    }

    public DureeCredit duree() {
        return duree;
    }

    public String idClient() {
        return idClient;
    }
}