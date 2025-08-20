package quantiLearn.progress_service.service;

import com.quantilearn.shareddtos.lesson_service.LessonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "lesson-service", path = "/lesson-service/api/v1/lesson")
public interface LessonFeignService {
    @GetMapping("/{id}")
    ResponseEntity<LessonDto> getLessonById(@PathVariable("id") Long id);
    // spring creates a proxy of this request
    // make the actual request using rest template
    // returns the response
}
