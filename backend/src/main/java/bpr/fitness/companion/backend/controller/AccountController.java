package bpr.fitness.companion.backend.controller;

import bpr.fitness.companion.backend.model.dto.Account;
import bpr.fitness.companion.backend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class will contain all Account operations
 */
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Create a new Account
     * @param account contains all Account details
     * @return Account
     */
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account savedAccount = accountService.createAccount(account);
        return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
    }

    /**
     * Get all Accounts
     * @return List<Account>
     */
    @GetMapping
    public List<Account> getAllAccount() {
        return accountService.getAllAccounts();
    }

    /**
     * Get a Account by id
     * @param id of the Account
     * @return Account
     */
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        Account account = accountService.getAccountById(id);
        if (account != null) {
            return new ResponseEntity<>(account, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Update an existing Account
     * @param id of the Account we want to update
     * @param accountDetails object that contains all details that we want to update
     * @return Account
     */
    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account accountDetails) {
        Account updatedAccount = accountService.updateAccount(id, accountDetails);
        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
    }

    /**
     * Delete a Account by id
     * @param id of the Account we want to delete
     * @return confirmation message
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<>("Account with id " + id + " deleted", HttpStatus.OK);
    }
}

