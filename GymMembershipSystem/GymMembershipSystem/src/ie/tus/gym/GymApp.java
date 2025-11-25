package ie.tus.gym;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.function.Predicate;

/**
 * MAIN APPLICATION
 * Demonstrates EVERYTHING:
 * - Interactive console input
 * - ArrayLists
 * - Lambdas / method references
 * - Pattern matching switch
 * - Checked + unchecked exceptions
 * - Using all classes together
 */
public class GymApp {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("=== Gym Membership System ===");

        var scheduler = new GymScheduler();          // LVTI
        var members   = new ArrayList<Member>();
        var trainers  = new ArrayList<Trainer>();
        var plans     = new ArrayList<WorkoutPlan>();

        // the trainers
        trainers.add(new Trainer(1L, "John Cena", "JohnCena100@icloud.com", 40.00));
        trainers.add(new Trainer(2L, "Usain Bolt", "UBolt75@gmail.com", 45.00));

        // the workout plans
        plans.add(WorkoutPlan.create(
                "Strength Training", "Squats", "Bench", "Deadlift"));
        plans.add(WorkoutPlan.create(
                "Cardio Focus", "Treadmill", "Row", "Bike"));

        boolean moreMembers = true;
        long nextId = 100;

        while (moreMembers) {

            System.out.println("\n--- Register new member ---");

            System.out.print("Name: ");
            String name = SCANNER.nextLine();

            System.out.print("Email: ");
            String email = SCANNER.nextLine();

            // choose membership interactively
            MembershipType type = chooseMembership();

            // PREMIUM uses PremiumMember class, others use Member
            Member member = (type == MembershipType.PREMIUM)
                    ? new PremiumMember(nextId, name, email)
                    : new Member(nextId, name, email, type);

            members.add(member);

            System.out.println("Registered: " + member);

            // choose trainer
            Trainer trainer = chooseTrainer(trainers);

            // choose workout plan

            // schedule for tomorrow at 18:00
            WorkoutSession session = new WorkoutSession(
                    member.id(),
                    trainer.id(),
                    LocalDate.now().plusDays(1),
                    LocalTime.of(18, 0),
                    60,
                    type
            );

            // book session 
            try {
                trainer.book(session);
                scheduler.addSession(session);
            } catch (InvalidSessionException ex) {
                System.out.println("Booking failed: " + ex.getMessage());
            }

            System.out.println("Monthly fee = " + member.formatPayment());

            System.out.print("Add another member? (y/n): ");
            moreMembers = SCANNER.nextLine().trim().toLowerCase().startsWith("y");
            nextId++;
        }

        // LAMBDA + PREDICATE example
        Predicate<Member> isPremium = m -> m.getMembershipType() == MembershipType.PREMIUM;

        System.out.println("\nPremium members:");
        members.forEach(m -> {
            if (isPremium.test(m)) {
                System.out.println(m);
            }
        });

        // PATTERN MATCHING SWITCH (
        System.out.println("\nPeople summary:");
        for (Person p : members) {
            String msg = switch (p) {
                case PremiumMember pm -> "Premium: " + pm.getName();
                case Member m         -> "Standard: " + m.getName();
                case Trainer t        -> "Trainer: " + t.getName();
            };
            System.out.println(msg);
        }

        // CHECKED EXCEPTION: saves summary
        try {
            scheduler.exportSummaryToFile(
                    Path.of("gym-summary.txt"), members, trainers);
            System.out.println("Summary saved to gym-summary.txt");
        } catch (IOException e) {
            System.out.println("File write error: " + e.getMessage());
        }

        System.out.println("\nThank you for using the Gym System!");
    }

   
    // Helper Menus
  

    private static MembershipType chooseMembership() {
        while (true) {
            System.out.println("\nChoose membership:");
            System.out.println("1) BASIC");
            System.out.println("2) PREMIUM");
            System.out.println("3) VIP");
            System.out.print("Choose: ");

            switch (SCANNER.nextLine().trim()) {
                case "1": return MembershipType.BASIC;
                case "2": return MembershipType.PREMIUM;
                case "3": return MembershipType.VIP;
            }
            System.out.println("Invalid. Try again.");
        }
    }

    private static Trainer chooseTrainer(List<Trainer> trainers) {
        while (true) {
            System.out.println("\nChoose trainer:");
            for (int i = 0; i < trainers.size(); i++) {
                System.out.println((i + 1) + ") " + trainers.get(i).getName());
            }

            System.out.print("Choose: ");
            try {
                int choice = Integer.parseInt(SCANNER.nextLine());
                if (choice >= 1 && choice <= trainers.size()) {
                    return trainers.get(choice - 1);
                }
            } catch (Exception ignored) {}

            System.out.println("Invalid. Try again.");
        }
    }

    @SuppressWarnings("unused")
	private static WorkoutPlan choosePlan(List<WorkoutPlan> plans) {
        while (true) {
            System.out.println("\nChoose workout plan:");
            for (int i = 0; i < plans.size(); i++) {
                System.out.println((i + 1) + ") " + plans.get(i).getName());
            }

            System.out.print("Choose: ");
            try {
                int choice = Integer.parseInt(SCANNER.nextLine());
                if (choice >= 1 && choice <= plans.size()) {
                    return plans.get(choice - 1);
                }
            } catch (Exception ignored) {}

            System.out.println("Invalid. Try again.");
        }
    }
}
