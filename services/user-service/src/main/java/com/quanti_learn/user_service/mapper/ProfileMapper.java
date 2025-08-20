package com.quanti_learn.user_service.mapper;

import com.quanti_learn.user_service.dto.ProfileDto;
import com.quanti_learn.user_service.entity.Profile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    Profile fromDto(ProfileDto profileDto);
    ProfileDto toDto(Profile profile);
}
