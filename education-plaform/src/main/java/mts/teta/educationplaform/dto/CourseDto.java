package mts.teta.educationplaform.dto;

public class CourseDto {
  private long id;
  private String author;
  private String title;

  public CourseDto() {

  }

  public CourseDto(final long id, final String author, final String title) {
    this.id = id;
    this.author = author;
    this.title = title;
  }

  public long getId() {
    return this.id;
  }

  public void setId(final long id) {
    this.id = id;
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
