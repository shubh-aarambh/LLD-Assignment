public class InvoicePrinter {
    public void print(String invoice) {
        System.out.print(invoice);
    }

    public void printSaved(String id, int lines) {
        System.out.println("Saved invoice: " + id + " (lines=" + lines + ")");
    }
}
