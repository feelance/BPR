package bpr.fitness.companion.backend.service;

import bpr.fitness.companion.backend.model.dto.Account;

import java.util.List;

public interface AccountService {
    /**
     * Create Account
     * @param account contains all user details
     * @return Account
     */
    Account createAccount(Account account);

    /**
     * Get all Accounts
     * @return List<Account>
     */
    List<Account> getAllAccounts();

    /**
     * Get Account by id
     * @param id of the user we want to get
     * @return Account
     */
    Account getAccountById(Long id);

    /**
     * Update Account
     * @param id of the Account we want to update
     * @param accountDetails contains all Account details
     * @return User
     */
    Account updateAccount(Long id, Account accountDetails);

    /**
     * Delete Account by id
     * @param id of the Account we want to delete
     */
    void deleteAccount(Long id);
}

