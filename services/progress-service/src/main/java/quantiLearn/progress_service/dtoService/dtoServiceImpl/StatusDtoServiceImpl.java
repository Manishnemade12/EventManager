package quantiLearn.progress_service.dtoService.dtoServiceImpl;

import org.springframework.stereotype.Service;
import quantiLearn.progress_service.dto.StatusDto;
import quantiLearn.progress_service.dtoService.StatusDtoService;
import quantiLearn.progress_service.mapper.StatusMapper;
import quantiLearn.progress_service.service.StatusService;

import java.util.List;

@Service
public class StatusDtoServiceImpl implements StatusDtoService {

    private final StatusMapper mapper;
    private final StatusService service;

    public StatusDtoServiceImpl(
            StatusMapper mapper,
            StatusService service
    ){
        this.mapper=mapper;
        this.service=service;
    }

    @Override
    public Long createStatus(StatusDto statusDto) {
        return service.createStatus(mapper.fromDto(statusDto)).getId();
    }

    @Override
    public StatusDto getStatusById(Long id) {
        return mapper.toDto(service.getStatusById(id));
    }

    @Override
    public List<StatusDto> getAllStatus() {
        return service.getAllStatus().stream().map(mapper::toDto).toList();
    }

    @Override
    public StatusDto updateStatus(StatusDto statusDto) {
        return mapper.toDto(service.updateStatus(mapper.fromDto(statusDto)));
    }

    @Override
    public void deleteStatus(Long id) {
        service.deleteStatus(id);
    }
}
