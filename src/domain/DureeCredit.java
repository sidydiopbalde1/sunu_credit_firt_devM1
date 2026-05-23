package domain;

public record DureeCredit(int mois) {

    public DureeCredit {

        if (mois < 3 || mois > 60) {
            throw new IllegalArgumentException(
                    "Durée invalide"
            );
        }
    }
}