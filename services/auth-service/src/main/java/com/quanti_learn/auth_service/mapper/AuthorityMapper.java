package com.quanti_learn.auth_service.mapper;

import com.quanti_learn.auth_service.dto.AuthorityDto;
import com.quanti_learn.auth_service.entity.Authority;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorityMapper {
    Authority fromDto(AuthorityDto authorityDto);
    AuthorityDto toDto(Authority authority);
}
