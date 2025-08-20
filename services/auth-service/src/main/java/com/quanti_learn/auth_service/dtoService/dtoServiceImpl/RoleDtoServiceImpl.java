package com.quanti_learn.auth_service.dtoService.dtoServiceImpl;

import com.quanti_learn.auth_service.dto.RoleDto;
import com.quanti_learn.auth_service.dtoService.RoleDtoService;
import com.quanti_learn.auth_service.mapper.RoleMapper;
import com.quanti_learn.auth_service.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleDtoServiceImpl implements RoleDtoService {

    private final RoleMapper roleMapper;
    private final RoleService roleService;

    public RoleDtoServiceImpl(
            RoleMapper roleMapper,
            RoleService roleService
    ){
        this.roleMapper=roleMapper;
        this.roleService=roleService;
    }

    @Override
    public Long createRole(RoleDto roleDto) {
        return roleService.createRole(roleMapper.fromDto(roleDto));
    }

    @Override
    public List<RoleDto> getAllRole() {
        return roleService.getAllRole().stream().map(roleMapper::toDto).toList();
    }

    @Override
    public void updateRole(RoleDto roleDto) {
        roleService.updateRole(roleMapper.fromDto(roleDto));
    }

    @Override
    public void addAuthorityToRole(String role, Set<String> authorities) {
        roleService.addAuthoritiesToRole(role,authorities);
    }

    @Override
    public void deleteRole(long id) {
        roleService.deleteRole(id);
    }

    @Override
    public RoleDto getRoleByName(String name) {
        return roleMapper.toDto(roleService.getRoleByName(name));
    }
}
