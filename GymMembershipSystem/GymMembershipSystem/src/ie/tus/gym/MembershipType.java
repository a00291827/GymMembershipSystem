package ie.tus.gym;

import java.util.Arrays;

/**
 * Enum representing membership levels.
 * Demonstrates:
 * - Complex enum with fields, constructors, methods
 * - Defensive copying of arrays
 */
public enum MembershipType {
    BASIC(30.0, new String[]{"Gym floor"}),
    PREMIUM(50.0, new String[]{"Gym floor", "Classes", "Pool"}),
    VIP(80.0, new String[]{"All areas", "Sauna", "PT discount"});

    private final double monthlyFee;
    private final String[] perks;

    MembershipType(double monthlyFee, String[] perks) {
        this.monthlyFee = monthlyFee;
        this.perks = perks.clone(); // defensive copy
    }

    public double monthlyFee() {
        return monthlyFee;
    }

    public String[] perks() {
        return perks.clone(); // defensive copy OUT
    }

    @Override
    public String toString() {
        return name() + " (€" + monthlyFee + ", perks=" + Arrays.toString(perks) + ")";
    }
}
