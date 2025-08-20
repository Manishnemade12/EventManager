package com.quanti_learn.user_service.service;

import com.quanti_learn.user_service.entity.Profile;

public interface ProfileService {
    Profile createProfile(Profile profile);
    Profile getProfile(String userId);
    void updateProfile(Profile profile);
    void deleteProfile(String userId);
}
