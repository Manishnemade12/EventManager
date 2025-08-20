package quantiLearn.progress_service.mapper;

import org.mapstruct.Mapper;
import quantiLearn.progress_service.dto.StatusDto;
import quantiLearn.progress_service.entity.Status;

@Mapper(componentModel = "spring")
public interface StatusMapper {
    Status fromDto(StatusDto statusDto);
    StatusDto toDto(Status status);
}
