package mts.teta.educationplaform;

import mts.teta.educationplaform.validator.TitleCaseValidatorEn;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EducationPlatformApplicationTests {

  @Autowired
  private TitleCaseValidatorEn titleCaseValidatorEn;

  @Test
  void contextLoads() {
    var b = titleCaseValidatorEn.isValid("Some String");
    System.out.println(b);
  }

}
