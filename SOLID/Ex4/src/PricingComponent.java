public interface PricingComponent {
    Money calculate(BookingRequest req);
}
