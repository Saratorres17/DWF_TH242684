package sv.edu.udb.controller.validation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
    private String regexPattern;
    @Override
    public void initialize(final PhoneNumber constraintAnnotation) {
        regexPattern = constraintAnnotation.pattern();
    }
    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if (phoneNumber == null) return false;
        return Pattern.compile(regexPattern).matcher(phoneNumber).matches();
    }
}