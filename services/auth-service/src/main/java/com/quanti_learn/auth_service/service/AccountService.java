package com.quanti_learn.auth_service.service;


import com.quanti_learn.auth_service.entity.Account;

import java.util.List;

public interface AccountService {
    Account createAccount(Account account);
    Account loginAccount(String email, String password);
    Account getAccount(String userId);
    List<Account> getAllAccount();
    void updateAccountEmail(long id, String email);
    void updateAccountPassword(long id, String password);
    void deleteAccount(long id);
    void createAdmin(Account account);
    void addRoleToUser(String userId, String roleName);
}
