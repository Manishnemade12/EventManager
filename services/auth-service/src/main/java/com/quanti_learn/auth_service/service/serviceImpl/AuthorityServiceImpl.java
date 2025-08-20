package com.quanti_learn.auth_service.service.serviceImpl;

import com.quanti_learn.auth_service.entity.Authority;
import com.quanti_learn.auth_service.exception.exceptions.CustomException;
import com.quanti_learn.auth_service.repository.AuthorityRepository;
import com.quanti_learn.auth_service.service.AuthorityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    public AuthorityServiceImpl(AuthorityRepository authorityRepository){
            this.authorityRepository=authorityRepository;
    }

    @Override
    public Authority createAuthority(Authority authority) {
        Optional<Authority> authorityOptional= authorityRepository.findByName(authority.getName());
        if(authorityOptional.isPresent()){
            throw new CustomException("Authority already existing");
        }
        return authorityRepository.save(authority);
    }

    @Override
    public List<Authority> getAllAuthority() {
        return authorityRepository.findAll();
    }

    @Override
    public void updateAuthority(Authority authority) {
        Optional<Authority> authorityOptional=authorityRepository.findById(authority.getId());
        if(authorityOptional.isPresent()){
            authorityRepository.save(authority);
        }
        else{
            throw new CustomException("Authority update failed!");
        }
    }

    @Override
    public void deleteAuthority(long id) {
        Optional<Authority> authorityOptional=authorityRepository.findById(id);
        if(authorityOptional.isPresent()){
            authorityRepository.delete(authorityOptional.get());
        }
        else{
            throw new CustomException("Authority deletion failed!");
        }
    }

    @Override
    public Authority getAuthorityByName(String authority) {
        Optional<Authority> authorityOptional=authorityRepository.findByName(authority);
        if(authorityOptional.isPresent()){
            return authorityOptional.get();
        }
        throw new CustomException("Authority not found");
    }

    @Override
    public Authority getAuthorityById(Long id) {
        Optional<Authority> authorityOptional=authorityRepository.findById(id);
        if(authorityOptional.isPresent()){
            return authorityOptional.get();
        }
        throw new CustomException("Authority not found!");
    }
}
