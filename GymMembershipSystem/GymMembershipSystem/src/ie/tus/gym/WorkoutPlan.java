package ie.tus.gym;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Immutable class.
 * Demonstrates:
 * - final class
 * - private final fields
 * - defensive copying
 * - static factory
 * - varargs
 */
public final class WorkoutPlan {

    private final String name;
    private final List<String> exercises;

    private WorkoutPlan(String name, List<String> exercises) {
        this.name = name;
        this.exercises = List.copyOf(exercises); // defensive copy
    }

    public static WorkoutPlan create(String name, String... exercises) { // varargs
        return new WorkoutPlan(name, List.of(exercises));
    }

    public String getName() {
        return name;
    }

    public List<String> getExercises() {
        return new ArrayList<>(exercises); // defensive copy OUT
    }

    @Override
    public String toString() {
        StringJoiner j = new StringJoiner(", ");
        exercises.forEach(j::add);
        return "WorkoutPlan{name='" + name + "', exercises=[" + j + "]}";
    }
}
