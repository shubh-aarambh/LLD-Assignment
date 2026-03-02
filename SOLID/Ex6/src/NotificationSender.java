public abstract class NotificationSender {
    protected final AuditLog audit;

    protected NotificationSender(AuditLog audit) {
        this.audit = audit;
    }

    /**
     * Sends a notification.
     * 
     * @throws NotificationException if sending fails for any reason (precondition
     *                               or transport).
     */
    public abstract void send(Notification n);
}
