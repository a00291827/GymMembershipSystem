package ie.tus.gym;

/**
 * Demonstrates:
 * - Interface
 * - Default method
 * - Static method
 */
public interface Payable {

    double calculatePayment();

    default String formatPayment() {
        return String.format("€%.2f", calculatePayment());
    }

    static double sum(Payable... payables) {
        double t = 0;
        for (Payable p : payables) t += p.calculatePayment();
        return t;
    }
}
