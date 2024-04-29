package mts.teta.educationplaform.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = TitleCaseAnnotationValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface TitleCase {
  LanguageCode code() default LanguageCode.ANY;
  String message() default "Invalid Title";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
