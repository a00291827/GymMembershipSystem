package ie.tus.gym;

/**
 * Trainer class.
 * Demonstrates:
 * - Implements multiple interfaces
 * - Default + static methods from interfaces
 * - Throws unchecked custom exception
 */
public final class Trainer extends Person implements Bookable, Payable {

    private double hourlyRate;

    public Trainer(long id, String name, String email, double hourlyRate) {
        super(id, name, email);
        this.hourlyRate = hourlyRate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double rate) {
        this.hourlyRate = rate;
    }

    @Override
    public void book(WorkoutSession session) {
        if (!canBookOn(session.date())) {
            throw new InvalidSessionException("Cannot book in the past: " + session.date());
        }

        logBooking(session);
        Bookable.printBookingConfirmation(session);
    }

    @Override
    public double calculatePayment() {
        // monthly estimate
        return hourlyRate * 10;
    }

    @Override
    public String toString() {
        return "Trainer{id=" + id() + ", name=" + getName() +
                ", hourlyRate=" + hourlyRate + "}";
    }
}
