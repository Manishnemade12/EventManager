package quantiLearn.progress_service.dtoService.dtoServiceImpl;

import com.quantilearn.eventmodels.SkillsObtained;
import com.quantilearn.shareddtos.lesson_service.LessonDto;
import com.quantilearn.shareddtos.lesson_service.SkillsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import quantiLearn.progress_service.dto.UserProgressionDto;
import quantiLearn.progress_service.dtoService.UserProgressionDtoService;
import quantiLearn.progress_service.mapper.UserProgressionMapper;
import quantiLearn.progress_service.service.KafkaPublisherService;
import quantiLearn.progress_service.service.LessonFeignService;
import quantiLearn.progress_service.service.UserProgressionService;

import java.util.List;

@Service
public class UserProgressionDtoServiceImpl implements UserProgressionDtoService {

    private final UserProgressionMapper mapper;
    private final UserProgressionService service;
    private final KafkaPublisherService publisherService;
    private final LessonFeignService lessonFeignService;

    public UserProgressionDtoServiceImpl(
            UserProgressionMapper mapper,
            UserProgressionService service,
            KafkaPublisherService publisherService,
            LessonFeignService lessonFeignService
    )
    {
        this.mapper=mapper;
        this.service=service;
        this.publisherService=publisherService;
        this.lessonFeignService=lessonFeignService;
    }

    @Override
    public Long createUserProgression(UserProgressionDto userProgression) {
        return this.service.createUserProgression(mapper.fromDto(userProgression)).getId();
    }

    @Override
    public UserProgressionDto getById(Long id) {
        return mapper.toDto(service.getById(id));
    }

    @Override
    public List<UserProgressionDto> getByUserId(String id) {
        return service.getByUserId(id).stream().map(mapper::toDto).toList();
    }

    @Override
    public UserProgressionDto updateUserProgression(UserProgressionDto userProgression) {
       UserProgressionDto userProgressionDto= mapper.toDto(service.updateUserProgression(mapper.fromDto(userProgression)));
       if(userProgressionDto.getStatus().getName().equals("Completed")){
           ResponseEntity<LessonDto> lessonDtoResponseEntity=lessonFeignService.getLessonById(userProgressionDto.getLessonId());
           if(lessonDtoResponseEntity.hasBody()) {
               LessonDto lessonDto=lessonDtoResponseEntity.getBody();
               SkillsObtained skillsObtained=SkillsObtained.builder()
                       .userId(userProgressionDto.getUserId())
                       .newSkills(lessonDto.getSkills().stream().map(SkillsDto::getName).toList())
                       .build();
               publisherService.publishMessage(skillsObtained);
           }
       }
       return userProgression;
    }

    @Override
    public void deleteUserProgressionById(Long id) {
        service.deleteUserProgressionById(id);
    }

    @Override
    public void deleteUserProgressionByUserId(String id) {
        service.deleteUserProgressionByUserId(id);
    }
}
