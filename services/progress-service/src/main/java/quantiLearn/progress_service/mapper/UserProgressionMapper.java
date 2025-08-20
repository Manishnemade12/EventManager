package quantiLearn.progress_service.mapper;

import org.mapstruct.Mapper;
import quantiLearn.progress_service.dto.UserProgressionDto;
import quantiLearn.progress_service.entity.UserProgression;

@Mapper(componentModel = "spring")
public interface UserProgressionMapper {
    UserProgression fromDto(UserProgressionDto userProgressionDto);
    UserProgressionDto toDto(UserProgression userProgression);
}
