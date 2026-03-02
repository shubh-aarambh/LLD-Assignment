public class AddOnPricingComponent implements PricingComponent {
    @Override
    public Money calculate(BookingRequest req) {
        double add = 0.0;
        for (AddOn a : req.addOns) {
            add += switch (a) {
                case MESS -> 1000.0;
                case LAUNDRY -> 500.0;
                case GYM -> 300.0;
            };
        }
        return new Money(add);
    }
}
