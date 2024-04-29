package mts.teta.educationplaform.config;

import mts.teta.educationplaform.dao.CourseRepository;
import mts.teta.educationplaform.service.CourseService;
import mts.teta.educationplaform.validator.TitleCaseValidatorEn;
import mts.teta.educationplaform.validator.TitleCaseValidatorRu;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
    CourseRepository.class,
    CourseService.class,
    TitleCaseValidatorRu.class,
    TitleCaseValidatorEn.class,
})
public class CourseConfig {
}
