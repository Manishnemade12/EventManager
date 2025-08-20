package com.quanti_learn.auth_service.service.serviceImpl;

import com.quanti_learn.auth_service.entity.Account;
import com.quanti_learn.auth_service.entity.Role;
import com.quanti_learn.auth_service.exception.exceptions.CustomException;
import com.quanti_learn.auth_service.exception.exceptions.CustomNotFoundException;
import com.quanti_learn.auth_service.repository.AccountRepository;
import com.quanti_learn.auth_service.service.AccountService;
import com.quanti_learn.auth_service.utils.Utils;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Utils utils;
    private final AuthenticationManager authenticationManager;
    private final RoleServiceImpl roleService;

    public AccountServiceImpl(
                            AccountRepository accountRepository,
                            BCryptPasswordEncoder bCryptPasswordEncoder,
                            Utils utils,
                            AuthenticationManager authenticationManager,
                            RoleServiceImpl roleService
    )
    {
        this.accountRepository=accountRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        this.utils=utils;
        this.authenticationManager=authenticationManager;
        this.roleService=roleService;
    }

    @Override
    public Account createAccount(Account account){
        if(accountRepository.findByEmail(account.getEmail()).isPresent()){
            throw new CustomException("Already exist");
        }
        else{
            Account newAccount= new Account();
            newAccount.setEmail(account.getEmail());
            newAccount.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
            newAccount.setUserId(utils.generateId(8));
            newAccount.setRoles(getRoles(List.of("ROLE_USER")));
            return accountRepository.save(newAccount);
        }
    }

    @Override
    public Account loginAccount(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,password
                )
        );
        return accountRepository.findByEmail(email).get();
    }

    @Override
    public Account getAccount(String userId) {
        Optional<Account> accountOptional=accountRepository.findByUserId(userId);
        if(accountOptional.isPresent()){
            return accountOptional.get();
        }
        throw new CustomNotFoundException("Account Not Found!");
    }

    @Override
    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }

    @Override
    public void updateAccountEmail(long id, String email) {
        Optional<Account> existAccountOptional=accountRepository.findById(id);
        if(existAccountOptional.isPresent()){
            Account existAccount=existAccountOptional.get();
            existAccount.setEmail(email);
            accountRepository.save(existAccount);
        }
        else{
            throw new CustomException("Email update failed!");
        }
    }

    @Override
    public void updateAccountPassword(long id, String password) {
        Optional<Account> existAccountOptional=accountRepository.findById(id);
        if(existAccountOptional.isPresent()){
            Account existAccount=existAccountOptional.get();
            existAccount.setPassword(bCryptPasswordEncoder.encode(password));
            accountRepository.save(existAccount);
        }
        else{
            throw new CustomException("Password update failed!");
        }
    }

    @Override
    public void deleteAccount(long id) {
        Optional<Account> existAccountOptional=accountRepository.findById(id);
        if(existAccountOptional.isPresent()){
            accountRepository.delete(existAccountOptional.get());
        }
        else{
            throw new CustomException("Account deletion failed!");
        }
    }

    @Override
    @Transactional
    public void createAdmin(Account adminAccount) {
        if(accountRepository.findByEmail(adminAccount.getEmail()).isPresent()){
            throw new CustomException("Admin Already exist");
        }
        else{
            Account newAccount= new Account();
            newAccount.setEmail(adminAccount.getEmail());
            newAccount.setPassword(bCryptPasswordEncoder.encode(adminAccount.getPassword()));
            newAccount.setUserId(utils.generateId(8));
            newAccount.setRoles(getRoles(List.of("ROLE_ADMIN")));
            accountRepository.save(newAccount);
        }
    }

    @Override
    @Transactional
    public void addRoleToUser(String userId, String roleName) {
        Account existingAccount=getAccount(userId);
        existingAccount.addNewRole(roleService.getRoleByName(roleName));
        accountRepository.save(existingAccount);
    }

    private Set<Role> getRoles(List<String> roles){
        Set<Role> existingRoles=new HashSet<>();
        roles.forEach(role->{
            existingRoles.add(roleService.getRoleByName(role));
        });
        return existingRoles;
    }

}
