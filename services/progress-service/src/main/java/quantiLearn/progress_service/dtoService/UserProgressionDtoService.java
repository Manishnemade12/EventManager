package quantiLearn.progress_service.dtoService;

import quantiLearn.progress_service.dto.UserProgressionDto;

import java.util.List;

public interface UserProgressionDtoService {
    Long createUserProgression(UserProgressionDto userProgression);
    UserProgressionDto getById(Long id);
    List<UserProgressionDto> getByUserId(String id);
    UserProgressionDto updateUserProgression(UserProgressionDto userProgression);
    void deleteUserProgressionById(Long id);
    void deleteUserProgressionByUserId(String id);
}
