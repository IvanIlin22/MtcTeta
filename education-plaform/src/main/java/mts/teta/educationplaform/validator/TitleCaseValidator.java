package mts.teta.educationplaform.validator;

public interface TitleCaseValidator {
  boolean isValid(String title);
  LanguageCode getCode();
}
