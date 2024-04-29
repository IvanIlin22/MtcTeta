package mts.teta.educationplaform.validator;

import java.util.Arrays;
import java.util.List;

public class TitleCaseValidatorRu extends TitleCaseValidatorImpl {
  private static final String RU_REGEX = "^[\"',:а-яА-ЯёЁ\\s]+$";

  @Override
  public boolean isValid(String title) {
    if (!super.isValid(title)) {
      return false;
    }

    if (!title.matches(RU_REGEX)) {
      return false;
    }
    List<String> words = Arrays.stream(title.split(" ")).toList();
    if (words.get(0).charAt(0) >= 'а' && words.get(0).charAt(0) <= 'я') {
      return false;
    }
    for (int i = 1; i < words.size(); i++) {
      if (words.get(i).charAt(0) >= 'А' && words.get(i).charAt(0) <= 'Я') {
        return false;
      }
    }

    return true;
  }

  @Override
  public LanguageCode getCode() {
    return LanguageCode.RU;
  }
}
