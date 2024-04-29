package mts.teta.educationplaform.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import mts.teta.educationplaform.validator.LanguageCode;
import mts.teta.educationplaform.validator.TitleCase;

public class CourseRequestToCreate {
  @NotBlank(message = "Course author has to be filled")
  @Size(min = 2, max = 128)
  private String author;
  @TitleCase(message = "Invalid title by some reason")
  private String title;


  public CourseRequestToCreate(final String title, final String author) {
    this.title = title;
    this.author = author;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(final String title) {
    this.title = title;
  }

  public String getAuthor() {
    return this.author;
  }

  public void setAuthor(final String author) {
    this.author = author;
  }
}
