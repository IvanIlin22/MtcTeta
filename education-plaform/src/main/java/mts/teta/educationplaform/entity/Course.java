package mts.teta.educationplaform.entity;

public class Course {
  private long id;
  private String author;
  private String title;

  public Course() {

  }

  public Course(final String author, final String title) {
    this.author = author;
    this.title = title;
  }

  public Course(final long id, final String author, final String title) {
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
