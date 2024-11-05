package bpr.fitness.companion.backend.mapper.impl;

import bpr.fitness.companion.backend.mapper.AccountMapper;
import bpr.fitness.companion.backend.model.dto.Account;
import bpr.fitness.companion.backend.model.entity.AccountEntity;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;

@Service
public class AccountMapperImpl implements AccountMapper {

    /**
     * Maps to AccountEntity
     * @param account contains all Account details
     * @return AccountEntity
     */
    @Override
    public AccountEntity mapToAccountEntity(Account account) {
        AccountEntity accountEntity = null;
        if (account != null) {
            accountEntity = new AccountEntity();
            accountEntity.setId(account.getId());
            accountEntity.setUserName(account.getUserName());
            accountEntity.setPassword(account.getPassword());
        }
        return accountEntity;
    }

    /**
     * Maps to Account
     * @param accountEntity AccountEntity
     * @return Account
     */
    @Override
    public Account mapToAccount(AccountEntity accountEntity) {
        Account account = null;
        if (accountEntity != null) {
            account = new Account();
            account.setId(accountEntity.getId());
            account.setUserName(accountEntity.getUserName());
            account.setPassword(accountEntity.getPassword());
        }
        return account;
    }

    /**
     * Maps to Account list
     * @param accountEntityList list of AccountEntity
     * @return List<Account>
     */
    @Override
    public List<Account> mapToAccountList(List<AccountEntity> accountEntityList) {
        List<Account> accountList = null;
        if (!CollectionUtils.isEmpty(accountEntityList)) {
            accountList = new ArrayList<>();
            for (AccountEntity accountEntity : accountEntityList) {
                accountList.add(mapToAccount(accountEntity));
            }
        }
        return accountList;
    }
}

