package domain;

public record GarantieDocument(String valeur) {

    public GarantieDocument{
        if(valeur == null || valeur.isBlank()){
            throw new IllegalArgumentException(
                    "Garantie invalide"
            );
        }
    }
}
