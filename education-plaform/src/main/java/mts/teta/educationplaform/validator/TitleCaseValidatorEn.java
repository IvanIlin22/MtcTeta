package mts.teta.educationplaform.validator;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class TitleCaseValidatorEn extends TitleCaseValidatorImpl {
  private static final String EN_REGEX = "^[\"',:a-zA-Z\s]+$";
  public static Set<String> PREPOSITIONS = Set.of("a", "but", "for", "or", "not", "the", "an");

  @Override
  public boolean isValid(String title) {
    if (!super.isValid(title)) {
      return false;
    }

    if (!title.matches(EN_REGEX)) {
      return false;
    }
    List<String> words = Arrays.stream(title.split(" ")).toList();
    if (isLowerCase(words.get(0)) || isLowerCase(words.get(words.size() - 1))) {
      return false;
    }

    for (String word : words) {
      if (isLowerCase(word) && !PREPOSITIONS.contains(word)) {
        return false;
      }
    }

    return true;
  }

  @Override
  public LanguageCode getCode() {
    return LanguageCode.EN;
  }

  private boolean isLowerCase(String str) {
    if (str.charAt(0) >= 'a' && str.charAt(0) <= 'z') {
      return true;
    }

    return false;
  }
}
