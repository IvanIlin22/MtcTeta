package mts.teta.educationplaform.validator;

import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public abstract class TitleCaseValidatorImpl implements TitleCaseValidator {
  private static final Set<String> INVALID_SYMBOLS = Set.of("\r", "\t", "\n");
  private static final Integer MAX_LENGTH = 1000;
  private static final Pattern MORE_1_SPACE_REGEX = Pattern.compile("\\s{2,}");

  @Override
  public boolean isValid(String title) {
    var b = !titleInvalid.test(title);
    return b;
  }

  private final Predicate<String> titleInvalid = (value -> INVALID_SYMBOLS.contains(value) ||
      !value.equals(value.trim())
      || value.length() > MAX_LENGTH
      || MORE_1_SPACE_REGEX.matcher(value).find()
  );
}
