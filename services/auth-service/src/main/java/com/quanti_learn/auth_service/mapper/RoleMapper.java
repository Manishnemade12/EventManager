package com.quanti_learn.auth_service.mapper;

import com.quanti_learn.auth_service.dto.RoleDto;
import com.quanti_learn.auth_service.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role fromDto(RoleDto roleDto);
    RoleDto toDto(Role role);
}
