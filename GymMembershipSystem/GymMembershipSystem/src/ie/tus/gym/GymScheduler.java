package ie.tus.gym;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Demonstrates:
 * - ArrayList usage
 * - Lambdas + Predicate
 * - Array usage
 * - Checked exception (IOException)
 * - Creating a human-readable summary including member + trainer names
 */
public class GymScheduler {

    private final List<WorkoutSession> sessions = new ArrayList<>();

    public void addSession(WorkoutSession session) {
        sessions.add(session);
    }

    // varargs example
    public void addSessions(WorkoutSession... newSessions) {
        for (WorkoutSession s : newSessions) {
            addSession(s);
        }
    }

    // Lambda + Predicate example
    public List<WorkoutSession> findSessions(Predicate<WorkoutSession> filter) {
        List<WorkoutSession> result = new ArrayList<>();
        for (WorkoutSession s : sessions) {
            if (filter.test(s)) result.add(s);
        }
        return result;
    }

    // LVTI used in loops
    public double totalPayments(List<? extends Payable> payables) {
        double total = 0;
        for (var p : payables) total += p.calculatePayment();
        return total;
    }

    /**
     * Builds a summary string that includes member names and trainer names.
     */
    public String summary(List<Member> members, List<Trainer> trainers) {
        StringBuilder sb = new StringBuilder();
        sb.append("Total sessions: ").append(sessions.size()).append("\n");

        // Map IDs 
        Map<Long, String> memberNames = new HashMap<>();
        for (Member m : members) {
            memberNames.put(m.id(), m.getName());
        }

        Map<Long, String> trainerNames = new HashMap<>();
        for (Trainer t : trainers) {
            trainerNames.put(t.id(), t.getName());
        }

        int[] durations = new int[sessions.size()]; // array example

        for (int i = 0; i < sessions.size(); i++) {
            WorkoutSession s = sessions.get(i);
            durations[i] = s.durationMinutes();

            String memberName  = memberNames.getOrDefault(s.memberId(),  "Member#"  + s.memberId());
            String trainerName = trainerNames.getOrDefault(s.trainerId(), "Trainer#" + s.trainerId());

            sb.append("- ")
              .append(memberName)
              .append(" with ")
              .append(trainerName)
              .append(" on ")
              .append(s.date())
              .append(" at ")
              .append(s.startTime())
              .append(" (")
              .append(s.durationMinutes())
              .append(" mins, ")
              .append(s.requiredMembership())
              .append(")\n");
        }

        return sb.toString();
    }

    
     // CHECKED exception example – writes the summary to a file.
     
    public void exportSummaryToFile(Path path,
                                    List<Member> members,
                                    List<Trainer> trainers) throws IOException {
        Files.writeString(path, summary(members, trainers));
    }
}
