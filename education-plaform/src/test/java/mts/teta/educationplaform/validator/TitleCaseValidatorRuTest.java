package mts.teta.educationplaform.validator;

import mts.teta.educationplaform.config.TestConfig;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class TitleCaseValidatorRuTest {

  @Autowired
  private TitleCaseValidatorRu titleCaseValidatorRu;

  @Test
  public void validTitle() {
    String title = "Корректный тайтл";
    TitleCaseValidatorRu titleCaseValidatorRu = new TitleCaseValidatorRu();

    assertTrue(titleCaseValidatorRu.isValid(title));
  }

  @Test
  public void notValidTitleWithen() {
    String title = "Некорректный тайтл With English";
    TitleCaseValidatorRu titleCaseValidatorRu = new TitleCaseValidatorRu();

    assertFalse(titleCaseValidatorRu.isValid(title));
  }

  @Test
  public void notValidTitleAllUpperCase() {
    String title = "Некорректный Тайтл";
    TitleCaseValidatorRu titleCaseValidatorRu = new TitleCaseValidatorRu();

    assertFalse(titleCaseValidatorRu.isValid(title));
  }

  @Test
  public void notValidTitleFirstLowerCase() {
    String title = "некорректный тайтл";
    TitleCaseValidatorRu titleCaseValidatorRu = new TitleCaseValidatorRu();

    assertFalse(titleCaseValidatorRu.isValid(title));
  }
}
