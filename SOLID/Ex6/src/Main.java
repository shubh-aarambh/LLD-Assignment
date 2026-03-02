public class Main {
    public static void main(String[] args) {
        System.out.println("=== Notification Demo ===");
        AuditLog audit = new AuditLog();

        Notification n = new Notification("Welcome", "Hello and SST!", "riya@sst.edu", "9876543210");
        // Wait, the original body was "Hello and welcome to SST!" (26 chars).
        // Let's keep it same to match output.
        Notification nOriginal = new Notification("Welcome", "Hello and welcome to SST!", "riya@sst.edu", "9876543210");

        NotificationSender email = new EmailSender(audit);
        NotificationSender sms = new SmsSender(audit);
        NotificationSender wa = new WhatsAppSender(audit);

        email.send(nOriginal);
        sms.send(nOriginal);
        try {
            wa.send(nOriginal);
        } catch (NotificationException ex) {
            System.out.println("WA ERROR: " + ex.getMessage());
            audit.add("WA failed");
        }

        System.out.println("AUDIT entries=" + audit.size());
    }
}
