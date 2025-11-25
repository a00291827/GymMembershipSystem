package ie.tus.gym;

/**
 * Standard member class.
 * Demonstrates:
 * - Inheritance
 * - Method overloading
 * - Constructors using this()
 * - super()
 * - Polymorphism (calculatePayment)
 */
public non-sealed class Member extends Person implements Payable {

    private MembershipType membershipType;

    // BASIC by default
    public Member(long id, String name, String email) {
        this(id, name, email, MembershipType.BASIC); // this()
    }

    public Member(long id, String name, String email, MembershipType membershipType) {
        super(id, name, email); // super()
        this.membershipType =
                (membershipType == null ? MembershipType.BASIC : membershipType);
    }

    public MembershipType getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(MembershipType type) {
        if (type != null) this.membershipType = type;
    }

    // Overloaded method
    public void upgrade() {
        upgrade(MembershipType.PREMIUM);
    }

    // Overloaded method
    public void upgrade(MembershipType newType) {
        setMembershipType(newType);
    }

    @Override
    public double calculatePayment() {
        // membershipType is guaranteed non-null
        return membershipType.monthlyFee();
    }

    @Override
    public String toString() {
        return "Member{id=" + id() + ", name=" + getName() +
                ", membership=" + membershipType + "}";
    }
}
