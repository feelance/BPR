package bpr.fitness.companion.backend.mapper;

import bpr.fitness.companion.backend.model.dto.Account;
import bpr.fitness.companion.backend.model.entity.AccountEntity;

import java.util.List;

public interface AccountMapper {

    /**
     * Maps to AccountEntity
     * @param account contains all Account details
     * @return AccountEntity
     */
    AccountEntity mapToAccountEntity(Account account);

    /**
     * Maps to Account list
     * @param accountEntityList list of AccountEntity
     * @return List<Account>
     */
    List<Account> mapToAccountList(List<AccountEntity> accountEntityList);

    /**
     * Maps to Account
     * @param accountEntity AccountEntity
     * @return Account
     */
    Account mapToAccount(AccountEntity accountEntity);
}

