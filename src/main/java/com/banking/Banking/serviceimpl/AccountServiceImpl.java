package com.banking.Banking.serviceimpl;

import com.banking.Banking.entity.Account;
import com.banking.Banking.repository.AccountRepository;
import com.banking.Banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Override
    public Account createAcc(Account account) {
        Account acc=new Account();

            acc.setAccountHolderName(account.getAccountHolderName());
            acc.setBalance(account.getBalance());
            Account save = accountRepository.save(acc);

            return save;

    }

    @Override
    public Account findById(long id) {
        Account account = accountRepository.findById(id).get();
        return account;
    }

    @Override
    public Account deposit(long id, Integer amount) {
        Account account = accountRepository.findById(id).get();
        if ( amount>0){
            Integer total=account.getBalance()+amount;
            account.setBalance(total);
            Account save = accountRepository.save(account);
            return  save;
        }
        return null;
    }

    @Override
    public Account withdraw(long id, int amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("id not found"));
        if (amount>0){
            int total = account.getBalance() - amount;
            account.setBalance(total);
            Account save = accountRepository.save(account);
            return save;
        }
        return null;
    }

    @Override
    public List<Account> findAll() {
        List<Account> all = accountRepository.findAll();
        return all;
    }

    @Override
    public String delete(long id) {
        Account account = accountRepository.findById(id).get();
        accountRepository.delete(account);
        return "Account Deleted";
    }
}
