package bpr.fitness.companion.backend.service.impl;

import bpr.fitness.companion.backend.exception.AccountNotFoundException;
import bpr.fitness.companion.backend.mapper.AccountMapper;
import bpr.fitness.companion.backend.model.dto.Account;
import bpr.fitness.companion.backend.model.entity.AccountEntity;
import bpr.fitness.companion.backend.repository.AccountRepository;
import bpr.fitness.companion.backend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper mapper;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper mapper) {
        this.accountRepository = accountRepository;
        this.mapper = mapper;
    }

    @Override
    public Account createAccount(Account account) {
        AccountEntity accountEntity = mapper.mapToAccountEntity(account);
        accountEntity = accountRepository.save(accountEntity);
        return mapper.mapToAccount(accountEntity);
    }

    @Override
    public List<Account> getAllAccounts() {
        List<AccountEntity> AccountEntities = accountRepository.findAll();
        return mapper.mapToAccountList(AccountEntities);
    }

    @Override
    public Account getAccountById(Long id) {
        Optional<AccountEntity> optAccountEntity = accountRepository.findById(id);
        return optAccountEntity.map(mapper::mapToAccount).orElse(null);
    }

    @Override
    public Account updateAccount(Long id, Account accountDetails) {
        if (!accountRepository.existsById(id)) {
            throw new AccountNotFoundException("Account with id " + id + " not found");
        }
        accountDetails.setId(id);
        AccountEntity accountEntity = mapper.mapToAccountEntity(accountDetails);
        accountRepository.save(accountEntity);
        return mapper.mapToAccount(accountEntity);
    }

    @Override
    public void deleteAccount(Long id) {
        if (!accountRepository.existsById(id)) {
            throw new AccountNotFoundException("Account with id " + id + " not found");
        }
        accountRepository.deleteById(id);
    }
}

