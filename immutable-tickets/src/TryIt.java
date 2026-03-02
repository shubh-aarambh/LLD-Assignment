import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

/**
 * Starter demo that shows why mutability is risky.
 *
 * After refactor:
 * - direct mutation should not compile (no setters)
 * - external modifications to tags should not affect the ticket
 * - service "updates" should return a NEW ticket instance
 */
public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        // 1. Create a ticket
        IncidentTicket t1 = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created: " + t1);

        // 2. Demonstrate "updates" return new instances
        IncidentTicket t2 = service.assign(t1, "agent@example.com");
        IncidentTicket t3 = service.escalateToCritical(t2);

        System.out.println("\nOriginal after pseudo-mutations: " + t1);
        System.out.println("Final version: " + t3);

        // 3. Demonstrate immutability - external tag mutation should have no effect
        try {
            List<String> tags = t3.getTags();
            tags.add("HACKED_FROM_OUTSIDE");
        } catch (UnsupportedOperationException e) {
            System.out
                    .println("\nCaught expected exception when trying to mutate tags: " + e.getClass().getSimpleName());
        }

        // Verify t3 tags remain unchanged if List.copyOf was used (which returns
        // unmodifiable list)
        System.out.println("Final version tags: " + t3.getTags());

        // 4. Demonstrate validation
        try {
            IncidentTicket.builder().id("BAD ID").build();
        } catch (IllegalArgumentException e) {
            System.out.println("\nCaught expected validation error: " + e.getMessage());
        }
    }
}
