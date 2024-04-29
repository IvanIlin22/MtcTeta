package mts.teta.educationplaform.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import mts.teta.educationplaform.validator.TitleCase;

public class CourseRequestToUpdate {
  @NotBlank(message = "Course author has to be filled")
  @Size(min = 2, max = 128)
  private String author;
  @TitleCase
  private String title;

  public CourseRequestToUpdate(final String author, final String title) {
    this.author = author;
    this.title = title;
  }

  public String getAuthor() {
    return this.author;
  }

  public void setAuthor(final String author) {
    this.author = author;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(final String title) {
    this.title = title;
  }
}
