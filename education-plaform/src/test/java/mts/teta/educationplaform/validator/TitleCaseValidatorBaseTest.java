package mts.teta.educationplaform.validator;

import java.util.ArrayList;
import java.util.List;
import mts.teta.educationplaform.config.TestConfig;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class TitleCaseValidatorBaseTest {
  private static final String[] INVALID_SYMBOLS = "! # $ % & ( ) * + \\ -. / ; < = > ? @ [ ] ^ _ ` { | }".split(" ");

  @Autowired
  private TitleCaseValidatorEn baseValidator;

  @Test
  public void notValidEmptyString() {
    String title = "";
    assertFalse(baseValidator.isValid(title));
  }

  @Test
  public void notValidCarriageReturnInTheMiddle() {
    String title = "Invalid\rString";
    assertFalse(baseValidator.isValid(title));
  }

  @Test
  public void notValidCarriageReturnInTheEnd() {
    String title = "Invalid String\r";
    assertFalse(baseValidator.isValid(title));
  }

  @Test
  public void notValidCarriageReturnInTheStart() {
    String title = "\rInvalid String";
    assertFalse(baseValidator.isValid(title));
  }

  @Test
  public void notValidTabInTheMiddle() {
    String title = "Invalid\tString";
    assertFalse(baseValidator.isValid(title));
  }

  @Test
  public void notValidTabInTheEnd() {
    String title = "Invalid String\t";
    assertFalse(baseValidator.isValid(title));
  }

  @Test
  public void notValidTabInTheStart() {
    String title = "\tInvalid String";
    assertFalse(baseValidator.isValid(title));
  }

  @Test
  public void notValidLineTransitionInTheMiddle() {
    String title = "Invalid\nString";
    assertFalse(baseValidator.isValid(title));
  }

  @Test
  public void notValidLineTransitionInTheEnd() {
    String title = "Invalid String\n";
    assertFalse(baseValidator.isValid(title));
  }

  @Test
  public void notValidLineTransitionInTheStart() {
    String title = "\nInvalid String";
    assertFalse(baseValidator.isValid(title));
  }

  @Test
  public void notValidWithTwoSpaces() {
    String title = "Invalid  String";
    assertFalse(baseValidator.isValid(title));
  }

  @Test
  public void notValidWithSpacesInStart() {
    String title = " Invalid String";
    assertFalse(baseValidator.isValid(title));
  }

  @Test
  public void notValidWithSpacesInEnd() {
    String title = "Invalid String ";
    assertFalse(baseValidator.isValid(title));
  }

  @Test
  public void notValidInvalidCharacters() {
    String title = "Invalid String";
    List<Boolean> isValid = new ArrayList<>();
    for (int i = 0; i < INVALID_SYMBOLS.length; i++) {
      String newTitle = title + INVALID_SYMBOLS[i];
      isValid.add(baseValidator.isValid(newTitle));
    }

    isValid.forEach(v -> assertFalse(v));
  }
}
