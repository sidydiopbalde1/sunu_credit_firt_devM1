package domain;

import java.math.BigDecimal;

public record Money(BigDecimal amount) {

    private static final BigDecimal MIN =
            new BigDecimal("50000");

    private static final BigDecimal MAX =
            new BigDecimal("5000000");

    public Money {

        if (amount == null) {
            throw new IllegalArgumentException(
                    "Montant requis"
            );
        }

        if (amount.compareTo(MIN) < 0
                || amount.compareTo(MAX) > 0) {

            throw new IllegalArgumentException(
                    "Montant hors limites métier"
            );
        }
    }

    public Money add(Money other) {
        return new Money(
                this.amount.add(other.amount)
        );
    }

    public Money percentage(int percent) {

        return new Money(
                this.amount.multiply(
                        BigDecimal.valueOf(percent)
                ).divide(BigDecimal.valueOf(100))
        );
    }
}
