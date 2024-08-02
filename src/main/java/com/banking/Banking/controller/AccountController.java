package com.banking.Banking.controller;

import com.banking.Banking.entity.Account;
import com.banking.Banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin("http://localhost:4200")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
        Account acc = accountService.createAcc(account);
        return new ResponseEntity<>(acc, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Account>> allAccounts(){
        List<Account> all = accountService.findAll();
        return new ResponseEntity<>(all,HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable long id){
        Account acc = accountService.findById(id);
        return new ResponseEntity<>(acc, HttpStatus.OK);
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<Account> deposit(@PathVariable long id, @RequestBody Map<String,Integer>request){
        Integer amount = request.get("amount");
        Account deposit = accountService.deposit(id, amount);
        return new ResponseEntity<>(deposit,HttpStatus.OK);
    }

    @PutMapping("/{id}/withdrawl")
    public ResponseEntity<Account> withdrawl(@PathVariable long id, @RequestBody Map<String,Integer>request){
        Integer amount = request.get("amount");
        Account withdraw = accountService.withdraw(id, amount);

        return new ResponseEntity<>(withdraw,HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id){
        String delete = accountService.delete(id);
        return new ResponseEntity<>(delete,HttpStatus.OK);

    }
}
