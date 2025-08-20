package com.quanti_learn.auth_service.utils;

import com.quanti_learn.auth_service.entity.Account;
import com.quanti_learn.auth_service.entity.Authority;
import com.quanti_learn.auth_service.entity.Role;
import com.quanti_learn.auth_service.exception.exceptions.CustomException;
import com.quanti_learn.auth_service.service.AccountService;
import com.quanti_learn.auth_service.service.AuthorityService;
import com.quanti_learn.auth_service.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class PreLoadData {

    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthorityService authorityService;

    public void loadRequiredData() throws Exception {

        var AUTHORITY_READ=loadAuthority("AUTHORITY_READ");
        var AUTHORITY_UPDATE=loadAuthority("AUTHORITY_UPDATE");
        var AUTHORITY_WRITE=loadAuthority("AUTHORITY_WRITE");
        var AUTHORITY_DELETE=loadAuthority("AUTHORITY_DELETE");

        Set<Authority> userAuthorities=new HashSet<>();
        userAuthorities.add(AUTHORITY_READ);

        Set<Authority> adminAuthorities=new HashSet<>();
        adminAuthorities.add(AUTHORITY_READ);
        adminAuthorities.add(AUTHORITY_WRITE);
        adminAuthorities.add(AUTHORITY_UPDATE);
        adminAuthorities.add(AUTHORITY_DELETE);

        // load roles
        loadRole("ROLE_USER",userAuthorities);
        loadRole("ROLE_ADMIN",adminAuthorities);

        //create admin
        try{
            System.out.println("creating admin");
            accountService.createAdmin(Account.builder().email("admin@gmail.com").password("admin@123").build());
        }
        catch (Exception e)
        {
            System.out.println("At creating admin!");
            System.out.println(e);
        }
    }

    private void loadRole(String role, Set<Authority> authorities){
        try{
            roleService.createRole(Role.builder().name(role).authorities(authorities).build());
        }catch (CustomException e){
            System.out.println(e);
        };
    }

    private Authority loadAuthority(String authority) {
        try {
            return authorityService.createAuthority(Authority.builder().name(authority).build());
        }
        catch (CustomException e){
            System.out.print(e);
            return authorityService.getAuthorityByName(authority);
        }
    }

}