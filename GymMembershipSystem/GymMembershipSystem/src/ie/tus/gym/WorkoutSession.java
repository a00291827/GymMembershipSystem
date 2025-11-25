package ie.tus.gym;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Record type.
 * Demonstrates:
 * - Modern Java record
 * - Automatic final fields, constructor, equals/hashCode/toString
 */
public record WorkoutSession(
        long memberId,
        long trainerId,
        LocalDate date,
        LocalTime startTime,
        int durationMinutes,
        MembershipType requiredMembership
) {}
