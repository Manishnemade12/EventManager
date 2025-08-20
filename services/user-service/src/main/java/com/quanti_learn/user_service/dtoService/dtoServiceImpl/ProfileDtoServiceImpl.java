package com.quanti_learn.user_service.dtoService.dtoServiceImpl;

import com.quanti_learn.user_service.dto.ProfileDto;
import com.quanti_learn.user_service.dtoService.ProfileDtoService;
import com.quanti_learn.user_service.mapper.ProfileMapper;
import com.quanti_learn.user_service.service.KafkaPublisherService;
import com.quanti_learn.user_service.service.ProfileService;
import org.springframework.stereotype.Service;

import com.quantilearn.eventmodels.ProfileCreatedDto;

@Service
public class ProfileDtoServiceImpl implements ProfileDtoService {

    private final ProfileMapper mapper;
    private final ProfileService service;
    private final KafkaPublisherService publisherService;

    public ProfileDtoServiceImpl(
        ProfileMapper profileMapper,
        ProfileService profileService,
        KafkaPublisherService publisherService
    ){
        this.mapper=profileMapper;
        this.service=profileService;
        this.publisherService=publisherService;
    }

    @Override
    public ProfileDto createProfile(ProfileDto profile) {
        profile.setId(null);
        ProfileDto res= mapper.toDto(service.createProfile(mapper.fromDto(profile)));
        ProfileCreatedDto profileCreatedDto= ProfileCreatedDto.builder()
                .email(profile.getEmail())
                .firstName(profile.getFirstName())
                .lastName(profile.getLastName())
                .build();
        publisherService.publishMessage(profileCreatedDto);
        return res;
    }

    @Override
    public ProfileDto getProfile(String userId) {
        return mapper.toDto(service.getProfile(userId));
    }

    @Override
    public void updateProfile(ProfileDto profile) {
        service.updateProfile(mapper.fromDto(profile));
    }

    @Override
    public void deleteProfile(String userId) {
        service.deleteProfile(userId);
    }
}
