package com.quanti_learn.auth_service.service;


import com.quanti_learn.auth_service.entity.Authority;

import java.util.List;

public interface AuthorityService {
    Authority createAuthority(Authority authority);
    List<Authority> getAllAuthority();
    void updateAuthority(Authority authority);
    void deleteAuthority(long id);
    Authority getAuthorityByName(String authority);
    Authority getAuthorityById(Long id);
}
