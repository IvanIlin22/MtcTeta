package mts.teta.educationplaform.resource;

import jakarta.validation.Valid;
import jakarta.ws.rs.QueryParam;
import java.util.List;
import static java.util.Objects.requireNonNullElse;
import javax.inject.Inject;
import mts.teta.educationplaform.dto.CourseDto;
import mts.teta.educationplaform.dto.CourseRequestToCreate;
import mts.teta.educationplaform.dto.CourseRequestToUpdate;
import mts.teta.educationplaform.service.CourseService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
public class CourseController {
  private final CourseService courseService;

  @Inject
  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  @GetMapping("")
  public List<CourseDto> courseTable() {
    return courseService.findAll();
  }

  @GetMapping("/{id:[0-9]+}")
  @ResponseBody
  public CourseDto getCourse(@PathVariable("id") long id) {
    return courseService.findById(id);
  }

  @GetMapping("/filter")
  public List<CourseDto> findByTitleWithPrefix(@QueryParam("titlePrefix") String titlePrefix) {
    return courseService.findByTitleWithPrefix(requireNonNullElse(titlePrefix, ""));
  }

  @PutMapping("/{id}")
  @ResponseBody
  public CourseDto updateCourse(@PathVariable("id") long id, @Valid @RequestBody CourseRequestToUpdate request) {
    return courseService.update(request, id);
  }

  @PostMapping("")
  @ResponseBody
  public CourseDto create(@Valid @RequestBody CourseRequestToCreate request) {
    return courseService.create(request);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable("id") long id) {
    courseService.delete(id);
  }
}
