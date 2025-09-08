package sv.edu.udb;
import org.junit.jupiter.api.Test;
import sv.edu.udb.controller.validation.PhoneNumber;
import sv.edu.udb.controller.validation.PhoneNumberValidator;
import static org.junit.jupiter.api.Assertions.*;
public class PhoneNumberValidatorTest {
    private final PhoneNumberValidator validator = new PhoneNumberValidator();
    @Test
    void testValidPhoneNumber() {
        PhoneNumber annotation = new PhoneNumber() {
            @Override public String message() { return "Invalid"; }
            @Override public String pattern() { return "^\\d{4}-\\d{4}$"; }
            @Override public Class<?>[] groups() { return new Class[0]; }
            @Override public Class<? extends java.lang.annotation.Annotation> annotationType() { return PhoneNumber.class; }
            @Override public Class<? extends jakarta.validation.Payload>[] payload() { return new Class[0]; }
        };
        validator.initialize(annotation);
        assertTrue(validator.isValid("1234-5678", null));
        assertFalse(validator.isValid("12345678", null));
    }
}