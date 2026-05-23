import java.util.List;
import java.util.stream.Stream;

import domain.DemandeCredit;
import domain.ImportateurFluxCredit;

public class App {
    public static void main(String[] args) throws Exception {
        ImportateurFluxCredit importateur =
                new ImportateurFluxCredit();

        Stream<String> flux = Stream.of(

                // valide
                "CL-001;1500000;12;TITRE_FONCIER,CONTRAT_TRAVAIL",

                // montant invalide
                "CL-002;1000;12;TITRE_FONCIER,CONTRAT_TRAVAIL",

                // durée invalide
                "CL-003;200000;1;TITRE_FONCIER,CONTRAT_TRAVAIL",

                // une seule garantie
                "CL-004;500000;12;TITRE_FONCIER",

                // montant texte
                "CL-005;UN_MILLION;12;TITRE_FONCIER,CONTRAT_TRAVAIL",

                // ligne vide
                "",

                // valide
                "CL-006;3000000;24;BULLETIN_SALAIRE,CONTRAT_TRAVAIL"

        );

        List<DemandeCredit> demandes =
                importateur.traiterFlux(flux);

        System.out.println(
                "Demandes valides : "
                        + demandes.size()
        );

        demandes.forEach(d ->
                System.out.println(
                        d.idClient()
                                + " | "
                                + d.montant().amount()
                                + " | "
                                + d.statut()
                )
        );
    }
}
