public class RoomPricingComponent implements PricingComponent {
    @Override
    public Money calculate(BookingRequest req) {
        double base = switch (req.roomType) {
            case LegacyRoomTypes.SINGLE -> 14000.0;
            case LegacyRoomTypes.DOUBLE -> 15000.0;
            case LegacyRoomTypes.TRIPLE -> 12000.0;
            default -> 16000.0;
        };
        return new Money(base);
    }
}
