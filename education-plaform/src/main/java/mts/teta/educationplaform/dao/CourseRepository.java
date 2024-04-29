package mts.teta.educationplaform.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import mts.teta.educationplaform.entity.Course;

public class CourseRepository {

  AtomicLong increment = new AtomicLong(2);
  private ConcurrentHashMap<Long, Course> courses = new ConcurrentHashMap<>(
      Map.of(
          1L, new Course(1, "Петров А.В.", "Основы кройки и шитья"),
          2L, new Course(2, "Мошкина А.В", "Введение в архитектурный дизайн")
          )
  );

  public List<Course> findAll() {
    return courses.entrySet().stream().map(Map.Entry::getValue).toList();
  }

  public Optional<Course> findById(long id) {
    return Optional.ofNullable(courses.get(id));
  }

  public List<Course> findByTitleWithPrefix(String prefix) {
    return courses.values().stream().filter(course -> course.getTitle().startsWith(prefix)).toList();
  }

  public void update(Course course) {
    courses.put(course.getId(), course);
  }

  public Course create(Course course) {
    long id = increment.addAndGet(1);
    courses.put(id, course);
    course.setId(id);

    return course;
  }

  public void delete(long id) {
    courses.remove(id);
  }
}
