package domain;



import java.util.stream.Stream;

public class OptionalDemande {

    private final DemandeCredit value;

    private OptionalDemande(DemandeCredit value) {
        this.value = value;
    }

    public static OptionalDemande of(
            DemandeCredit demande
    ) {

        return new OptionalDemande(demande);
    }

    public static OptionalDemande empty() {

        return new OptionalDemande(null);
    }

    public Stream<DemandeCredit> stream() {

        return value == null
                ? Stream.empty()
                : Stream.of(value);
    }
}
