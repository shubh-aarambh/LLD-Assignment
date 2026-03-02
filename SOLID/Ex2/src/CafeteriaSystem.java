import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceStore store;
    private final PricingCalculator calculator;
    private final InvoicePrinter printer;
    private int invoiceSeq = 1000;

    public CafeteriaSystem(InvoiceStore store) {
        this.store = store;
        this.calculator = new PricingCalculator();
        this.printer = new InvoicePrinter();
    }

    public void addToMenu(MenuItem i) {
        menu.put(i.id, i);
    }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);
        StringBuilder out = new StringBuilder();
        out.append("Invoice# ").append(invId).append("\n");

        double subtotal = calculator.calculateSubtotal(lines, menu, out);

        double taxPct = TaxRules.taxPercent(customerType);
        double tax = calculator.calculateTax(subtotal, customerType);

        double discount = calculator.calculateDiscount(customerType, subtotal, lines.size());

        double total = subtotal + tax - discount;

        out.append(String.format("Subtotal: %.2f\n", subtotal));
        out.append(String.format("Tax(%.0f%%): %.2f\n", taxPct, tax));
        out.append(String.format("Discount: -%.2f\n", discount));
        out.append(String.format("TOTAL: %.2f\n", total));

        String printable = InvoiceFormatter.identityFormat(out.toString());
        printer.print(printable);

        store.save(invId, printable);
        printer.printSaved(invId, store.countLines(invId));
    }
}
