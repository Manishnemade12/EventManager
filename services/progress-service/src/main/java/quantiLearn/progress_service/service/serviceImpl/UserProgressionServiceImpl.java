package quantiLearn.progress_service.service.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import quantiLearn.progress_service.entity.Status;
import quantiLearn.progress_service.entity.UserProgression;
import quantiLearn.progress_service.exception.exceptions.CustomException;
import quantiLearn.progress_service.exception.exceptions.CustomNotFoundException;
import quantiLearn.progress_service.repository.StatusRepository;
import quantiLearn.progress_service.repository.UserProgressionRepository;
import quantiLearn.progress_service.service.UserProgressionService;

import java.util.List;
import java.util.Optional;

@Service
public class UserProgressionServiceImpl implements UserProgressionService {

    private final UserProgressionRepository repository;
    private final StatusRepository statusRepository;

    public UserProgressionServiceImpl(
            UserProgressionRepository repository,
            StatusRepository statusRepository
    ){
        this.repository=repository;
        this.statusRepository=statusRepository;
    }

    @Override
    @Transactional
    public UserProgression createUserProgression(UserProgression userProgression) {
        userProgression.setId(null);
        Long statusId = userProgression.getStatus().getId();
        Status managedStatus = statusRepository.findById(statusId)
                .orElseThrow(() -> new RuntimeException("Invalid status ID: " + statusId));

        userProgression.setStatus(managedStatus); // Use managed entity
       return repository.save(userProgression);
    }

    @Override
    public UserProgression getById(Long id) {
        Optional<UserProgression> optionalUserProgression=repository.findById(id);
        if(optionalUserProgression.isPresent()){
            return optionalUserProgression.get();
        }
        throw new CustomNotFoundException("User Progression not found!");
    }

    @Override
    public List<UserProgression> getByUserId(String id) {
        return repository.findAllByUserId(id);
    }

    @Override
    @Transactional
    public UserProgression updateUserProgression(UserProgression userProgression) {
        Optional<UserProgression> optionalUserProgression=repository.findById(userProgression.getId());
        if(optionalUserProgression.isPresent()){
            return repository.save(userProgression);
        }
        throw new CustomException("UserProgression update failed!");
    }

    @Override
    public void deleteUserProgressionById(Long id) {
        Optional<UserProgression> optionalUserProgression=repository.findById(id);
        if(optionalUserProgression.isPresent()){
            repository.delete(optionalUserProgression.get());
        }
        else{
            throw new CustomException("UserProgression delete failed!");
        }
    }

    @Override
    public void deleteUserProgressionByUserId(String id) {
        Optional<UserProgression> optionalUserProgression = repository.findByUserId(id);
        if (optionalUserProgression.isPresent()) {
            repository.deleteAllByUserId(id);
        } else {
            throw new CustomException("UserProgression delete failed!");
        }
    }
}
