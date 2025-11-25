package ie.tus.gym;

import java.time.LocalDate;

/**
 * Demonstrates:
 * - Interface default methods
 * - Private interface method
 * - Static interface method
 */
public interface Bookable {

    void book(WorkoutSession session);

    default boolean canBookOn(LocalDate date) {
        return !date.isBefore(LocalDate.now());
    }

    default void logBooking(WorkoutSession session) {
        logInternal("Booked " + session.date() + " at " + session.startTime());
    }

    static void printBookingConfirmation(WorkoutSession session) {
        System.out.println("[CONFIRMED] " + session);
    }

    private static void logInternal(String msg) {
        System.out.println("[LOG] " + msg);
    }
}
