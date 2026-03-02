import java.util.*;

public class PricingCalculator {
    public double calculateSubtotal(List<OrderLine> lines, Map<String, MenuItem> menu, StringBuilder out) {
        double subtotal = 0.0;
        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            subtotal += lineTotal;
            out.append(String.format("- %s x%d = %.2f\n", item.name, l.qty, lineTotal));
        }
        return subtotal;
    }

    public double calculateTax(double subtotal, String customerType) {
        double taxPct = TaxRules.taxPercent(customerType);
        return subtotal * (taxPct / 100.0);
    }

    public double calculateDiscount(String customerType, double subtotal, int distinctLines) {
        return DiscountRules.discountAmount(customerType, subtotal, distinctLines);
    }
}
