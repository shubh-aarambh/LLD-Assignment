import java.util.Optional;

public interface EligibilityRule {
    Optional<String> evaluate(StudentProfile s);
}
