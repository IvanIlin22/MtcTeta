package mts.teta.educationplaform.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TitleCaseAnnotationValidator implements ConstraintValidator<TitleCase, String> {
  private LanguageCode languageCode;
  private Map<LanguageCode, TitleCaseValidator> validatorMap;

  @Autowired
  public TitleCaseAnnotationValidator(List<TitleCaseValidator> validators) {
    validatorMap = validators.stream()
        .collect(Collectors.toMap(TitleCaseValidator::getCode, Function.identity()));
  }

  @Override
  public void initialize(TitleCase constraintAnnotation) {
    languageCode = constraintAnnotation.code();
  }

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    TitleCaseValidator validator = validatorMap.get(languageCode);
    if (validator == null) {
      return !validatorMap.values().stream().filter(v -> v.isValid(s)).toList().isEmpty();
    }

    return validator.isValid(s);
  }
}
