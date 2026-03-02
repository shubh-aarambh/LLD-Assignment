import java.util.Optional;

public class AttendanceRule implements EligibilityRule {
    @Override
    public Optional<String> evaluate(StudentProfile s) {
        if (s.attendancePct < 75) {
            return Optional.of("attendance below 75");
        }
        return Optional.empty();
    }
}
