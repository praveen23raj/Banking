package com.banking.Banking.service;

import com.banking.Banking.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {

    Account createAcc(Account account);

    Account findById(long id);

    Account deposit(long id, Integer amount );

    Account withdraw(long id, int amount);

    List<Account> findAll();

    String delete(long id);
}
