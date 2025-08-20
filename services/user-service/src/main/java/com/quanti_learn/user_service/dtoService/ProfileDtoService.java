package com.quanti_learn.user_service.dtoService;

import com.quanti_learn.user_service.dto.ProfileDto;

public interface ProfileDtoService {
    ProfileDto createProfile(ProfileDto profile);
    ProfileDto getProfile(String userId);
    void updateProfile(ProfileDto profile);
    void deleteProfile(String userId);
}
