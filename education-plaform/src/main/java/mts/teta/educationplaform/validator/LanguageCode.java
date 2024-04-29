package mts.teta.educationplaform.validator;

public enum LanguageCode {
  ANY(0),
  RU(1),
  EN(2);

  private int codeValue;

  LanguageCode(int codeValue) {
    this.codeValue = codeValue;
  }

  public int getCodeValue() {
    return codeValue;
  }
}
