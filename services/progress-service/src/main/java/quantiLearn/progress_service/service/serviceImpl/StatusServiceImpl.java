package quantiLearn.progress_service.service.serviceImpl;

import org.springframework.stereotype.Service;
import quantiLearn.progress_service.entity.Status;
import quantiLearn.progress_service.exception.exceptions.CustomException;
import quantiLearn.progress_service.exception.exceptions.CustomNotFoundException;
import quantiLearn.progress_service.repository.StatusRepository;
import quantiLearn.progress_service.service.StatusService;

import java.util.List;
import java.util.Optional;

@Service
public class StatusServiceImpl implements StatusService {

    private final StatusRepository repository;

    public StatusServiceImpl(
            StatusRepository repository
    ){
        this.repository=repository;
    }

    @Override
    public Status createStatus(Status status) {
        status.setId(null);
        return repository.save(status);
    }

    @Override
    public Status getStatusById(Long id) {
        Optional<Status> optionalStatus=repository.findById(id);
        if(optionalStatus.isPresent()){
            return optionalStatus.get();
        }
        throw new CustomNotFoundException("Status not found!");
    }

    @Override
    public List<Status> getAllStatus() {
        return repository.findAll();
    }

    @Override
    public Status updateStatus(Status status) {
        Optional<Status> optionalStatus=repository.findById(status.getId());
        if(optionalStatus.isPresent()){
            return repository.save(status);
        }
        throw new CustomException("Status Update failed!");
    }

    @Override
    public void deleteStatus(Long id) {
        Optional<Status> optionalStatus=repository.findById(id);
        if(optionalStatus.isPresent()){
            repository.delete(optionalStatus.get());
        }
        else {
            throw new CustomException("Status Delete failed!");
        }
    }
}
