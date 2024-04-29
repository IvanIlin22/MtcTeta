package mts.teta.educationplaform.validator;

import java.util.ArrayList;
import java.util.List;
import mts.teta.educationplaform.config.TestConfig;
import static mts.teta.educationplaform.validator.TitleCaseValidatorEn.PREPOSITIONS;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class TitleCaseValidatorEnTest {

  @Autowired
  public TitleCaseValidatorEn titleCaseValidatorEn;

  @Test
  public void validTitle() {
    String title = "Valid Tittle With Three Words";

    assertTrue(titleCaseValidatorEn.isValid(title));
  }

  @Test
  public void validTitlePrepositionsLowerCase() {
    String title = "Valid Tittle %s In The Title";
    List<Boolean> isValid = new ArrayList<>();
    for (String word : PREPOSITIONS) {
      String newTitle = String.format(title, word);
      isValid.add(titleCaseValidatorEn.isValid(newTitle));
    }

    isValid.forEach(b -> assertTrue(b));
  }

  @Test
  public void notValidTitleLowerCaseInTheMiddle() {
    String title = "Not valid Tittle";

    assertFalse(titleCaseValidatorEn.isValid(title));
  }

  @Test
  public void notValidTitleLowerCaseInTheFirst() {
    String title = "not Valid Tittle";

    assertFalse(titleCaseValidatorEn.isValid(title));
  }

  @Test
  public void notValidTitleLowerCaseInTheEnd() {
    String title = "Not Valid tittle";

    assertFalse(titleCaseValidatorEn.isValid(title));
  }

  @Test
  public void notValidTitlePrepositionsLowerCaseInTheEnd() {
    String title = "Not Valid Tittle for";

    assertFalse(titleCaseValidatorEn.isValid(title));
  }

  @Test
  public void notValidTitlePrepositionsLowerCaseInTheStart() {
    String title = "for Not Valid Tittle";

    assertFalse(titleCaseValidatorEn.isValid(title));
  }

  @Test
  public void notValidTitleWithRu() {
    String title = "Valid Tittle с русскими словами With Three Words";

    assertFalse(titleCaseValidatorEn.isValid(title));
  }
}
