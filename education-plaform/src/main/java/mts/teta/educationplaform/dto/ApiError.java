package mts.teta.educationplaform.dto;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class ApiError {
  private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
  private String message;
  private String dateTime;


  public ApiError() {}

  public ApiError(final String message, final OffsetDateTime dateTime) {
    this.message = message;
    this.dateTime = dateTime.format(formatter);
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(final String message) {
    this.message = message;
  }

  public String getDateTime() {
    return this.dateTime;
  }

  public void setStatus(final OffsetDateTime dateTime) {
    this.dateTime = dateTime.format(formatter);
  }
}
