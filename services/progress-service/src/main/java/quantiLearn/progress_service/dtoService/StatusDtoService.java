package quantiLearn.progress_service.dtoService;

import quantiLearn.progress_service.dto.StatusDto;

import java.util.List;

public interface StatusDtoService {
    Long createStatus(StatusDto statusDto);
    StatusDto getStatusById(Long id);
    List<StatusDto> getAllStatus();
    StatusDto updateStatus(StatusDto statusDto);
    void deleteStatus(Long id);
}
