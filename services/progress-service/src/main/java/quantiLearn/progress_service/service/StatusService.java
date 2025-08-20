package quantiLearn.progress_service.service;

import quantiLearn.progress_service.entity.Status;

import java.util.List;

public interface StatusService {
    Status createStatus(Status status);
    Status getStatusById(Long id);
    List<Status> getAllStatus();
    Status updateStatus(Status status);
    void deleteStatus(Long id);
}
