package domain;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ImportateurFluxCredit {

    public List<DemandeCredit> traiterFlux(
            Stream<String> lignesBrutes
    ) {

        return lignesBrutes

              
                .filter(line ->
                        line != null &&
                        !line.isBlank()
                )

               
                .map(this::parserLigne)

                // ignore erreurs
                .flatMap(OptionalDemande::stream)

                .collect(Collectors.toList());
    }

    private OptionalDemande parserLigne(String ligne) {

        try {

            String[] parts = ligne.split(";");

            String idClient = parts[0];

            BigDecimal montant =
                    new BigDecimal(parts[1]);

            int duree =
                    Integer.parseInt(parts[2]);

            List<GarantieDocument> garanties =
                    Arrays.stream(parts[3].split(","))
                            .map(GarantieDocument::new)
                            .toList();

            DemandeCredit demande =
                    new DemandeCredit(
                            idClient,
                            new Money(montant),
                            new DureeCredit(duree),
                            garanties
                    );

            return OptionalDemande.of(demande);

        } catch (Exception e) {

        
            return OptionalDemande.empty();
        }
    }
}