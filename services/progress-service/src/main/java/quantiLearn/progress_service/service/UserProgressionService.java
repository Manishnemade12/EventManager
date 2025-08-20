package quantiLearn.progress_service.service;

import quantiLearn.progress_service.entity.UserProgression;

import java.util.List;

public interface UserProgressionService {
    UserProgression createUserProgression(UserProgression userProgression);
    UserProgression getById(Long id);
    List<UserProgression> getByUserId(String id);
    UserProgression updateUserProgression(UserProgression userProgression);
    void deleteUserProgressionById(Long id);
    void deleteUserProgressionByUserId(String id);
}
