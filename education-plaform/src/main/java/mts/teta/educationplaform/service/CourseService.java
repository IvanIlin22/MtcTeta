package mts.teta.educationplaform.service;

import java.util.List;
import javax.inject.Inject;
import mts.teta.educationplaform.dao.CourseRepository;
import mts.teta.educationplaform.dto.CourseDto;
import mts.teta.educationplaform.dto.CourseRequestToCreate;
import mts.teta.educationplaform.dto.CourseRequestToUpdate;
import mts.teta.educationplaform.entity.Course;

public class CourseService {
  private final CourseRepository courseRepository;

  @Inject
  public CourseService(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  public List<CourseDto> findAll() {
    return courseRepository.findAll().stream().map(c -> new CourseDto(c.getId(), c.getAuthor(), c.getTitle())).toList();
  }

  public CourseDto findById(long id) {
    return courseRepository.findById(id).map(c -> new CourseDto(c.getId(), c.getAuthor(), c.getTitle())).
        orElseThrow();
  }

  public List<CourseDto> findByTitleWithPrefix(String prefix) {
    return courseRepository.findByTitleWithPrefix(prefix).stream()
        .map(c -> new CourseDto(c.getId(), c.getAuthor(), c.getTitle())).toList();
  }

  public CourseDto update(CourseRequestToUpdate request, long id) {
    Course course = courseRepository.findById(id).orElseThrow();
    course.setAuthor(request.getAuthor());
    course.setTitle(request.getTitle());

    courseRepository.update(course);
    return new CourseDto(course.getId(), course.getAuthor(), course.getTitle());
  }

  public CourseDto create(CourseRequestToCreate request) {
    Course course = mapper(request);
    course = courseRepository.create(course);

    return new CourseDto(course.getId(), course.getAuthor(), course.getTitle());
  }

  public void delete(long id) {
    Course course = courseRepository.findById(id).orElseThrow();
    courseRepository.delete(id);
  }

  private Course mapper(CourseRequestToCreate request) {
    return new Course(request.getAuthor(), request.getTitle());
  }

}
