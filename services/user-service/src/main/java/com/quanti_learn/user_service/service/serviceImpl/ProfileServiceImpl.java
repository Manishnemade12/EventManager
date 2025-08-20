package com.quanti_learn.user_service.service.serviceImpl;

import com.quanti_learn.user_service.entity.Profile;
import com.quanti_learn.user_service.exception.exceptions.CustomNotFoundException;
import com.quanti_learn.user_service.repository.ProfileRepository;
import com.quanti_learn.user_service.service.ProfileService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository repository;

    public ProfileServiceImpl(
            ProfileRepository profileRepository
    ){
        this.repository=profileRepository;
    }

    @Override
    public Profile createProfile(Profile profile) {
        return repository.save(profile);
    }

    @Override
    public Profile getProfile(String userId) {
        Optional<Profile> profileOptional=repository.findByUserId(userId);
        if(profileOptional.isPresent()){
            return profileOptional.get();
        }
        throw new CustomNotFoundException("Profile Not found!");
    }

    @Override
    @Transactional
    public void updateProfile(Profile profile) {
        Optional<Profile> profileOptional=repository.findById(profile.getId());
        if(profileOptional.isPresent()){
            Profile existingProfile=profileOptional.get();
            existingProfile.setFirstName(profile.getFirstName());
            existingProfile.setLastName(profile.getLastName());
            existingProfile.setGitHub(profile.getGitHub());
            existingProfile.setLinkedIn(profile.getLinkedIn());
            existingProfile.setLearningGoal(profile.getLearningGoal());
            existingProfile.setTopicInterested(profile.getTopicInterested());
            repository.save(existingProfile);
        }
        else{
            throw new CustomNotFoundException("Profile Update failed!");
        }
    }

    @Override
    public void deleteProfile(String userId) {
        Optional<Profile> profileOptional=repository.findByUserId(userId);
        if(profileOptional.isPresent()){
            repository.delete(profileOptional.get());
        }
        else{
            throw new CustomNotFoundException("Profile Not found!");
        }
    }
}
