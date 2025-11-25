package ie.tus.gym;

/**
 * Premium member with discount.
 * Demonstrates:
 * - Polymorphism (overridden method)
 * - super.method() usage
 */
public final class PremiumMember extends Member {

    private static final double DISCOUNT = 0.10;

    public PremiumMember(long id, String name, String email) {
        super(id, name, email, MembershipType.PREMIUM);
    }

    @Override
    public double calculatePayment() {
        return super.calculatePayment() * (1.0 - DISCOUNT);
    }
}
