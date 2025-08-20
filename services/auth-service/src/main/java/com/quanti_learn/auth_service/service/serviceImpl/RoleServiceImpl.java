package com.quanti_learn.auth_service.service.serviceImpl;

import com.quanti_learn.auth_service.entity.Authority;
import com.quanti_learn.auth_service.entity.Role;
import com.quanti_learn.auth_service.exception.exceptions.CustomException;
import com.quanti_learn.auth_service.exception.exceptions.CustomNotFoundException;
import com.quanti_learn.auth_service.repository.RoleRepository;
import com.quanti_learn.auth_service.service.AuthorityService;
import com.quanti_learn.auth_service.service.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final AuthorityService authorityService;

    public RoleServiceImpl(
            RoleRepository roleRepository,
            AuthorityService authorityService
    ){
        this.roleRepository=roleRepository;
        this.authorityService=authorityService;
    }


    @Override
    @Transactional
    public Long createRole(Role role) {
        if(roleRepository.findByName(role.getName()).isPresent())
            throw new CustomException("Role Already exist");
        else {
            Set<Authority> authorities=new HashSet<>();
            role.getAuthorities().forEach(authority -> {
                if(authority.getAuthority().isEmpty())
                    authorities.add(authorityService.getAuthorityById(authority.getId()));
                else
                    authorities.add(authorityService.getAuthorityByName(authority.getAuthority()));
            });
            role.setAuthorities(authorities);
            return roleRepository.save(role).getId();
        }
    }

    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public void updateRole(Role role) {
        Optional<Role> existRoleOptional=roleRepository.findById(role.getId());
        if(existRoleOptional.isPresent()){
            if(roleRepository.findByName(role.getName()).isPresent())
                throw new CustomException("The new Role Already exist");
            roleRepository.save(role);
        }
        else{
            throw new CustomException("Role update failed!");
        }
    }

    @Override
    public void addAuthoritiesToRole(String role, Set<String> authorities) {
        Optional<Role> existRole=roleRepository.findByName(role);
        if(existRole.isPresent()){
            Set<Authority> existAuthorities=new HashSet<>();
            authorities.forEach((authority)->{
                existAuthorities.add(authorityService.getAuthorityByName(authority));
            });
            Role updatedRole=existRole.get();
            updatedRole.setAuthorities(existAuthorities);
            roleRepository.save(updatedRole);
        }
    }

    @Override
    public void deleteRole(long id) {
        Optional<Role> existRoleOptional=roleRepository.findById(id);
        if(existRoleOptional.isPresent()){
            roleRepository.delete(existRoleOptional.get());
        }
        else{
            throw new CustomException("Role delete failed!");
        }
    }

    @Override
    public Role getRoleByName(String name) {
        Optional<Role> existRole=roleRepository.findByName(name);
        if(existRole.isPresent()){
            return existRole.get();
        }
        else
            throw new CustomNotFoundException("Default Role not found");
    }
}
