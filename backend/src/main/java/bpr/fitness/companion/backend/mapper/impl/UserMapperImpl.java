package bpr.fitness.companion.backend.mapper.impl;

import bpr.fitness.companion.backend.mapper.UserMapper;
import bpr.fitness.companion.backend.model.dto.User;
import bpr.fitness.companion.backend.model.entity.AccountEntity;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;

@Service
public class UserMapperImpl implements UserMapper {

    /**
     * Maps to UserEntity
     * @param user contains all user details
     * @return UserEntity
     */
    @Override
    public AccountEntity mapToUserEntity(User user) {
        AccountEntity accountEntity = null;
        if (user != null) {
            accountEntity = new AccountEntity();
            accountEntity.setId(user.getId());
            accountEntity.setUserName(user.getUserName());
            accountEntity.setPassword(user.getPassword());
        }
        return accountEntity;
    }

    /**
     * Maps to User
     * @param accountEntity UserEntity
     * @return User
     */
    @Override
    public User mapToUser(AccountEntity accountEntity) {
        User user = null;
        if (accountEntity != null) {
            user = new User();
            user.setId(accountEntity.getId());
            user.setUserName(accountEntity.getUserName());
            user.setPassword(accountEntity.getPassword());
        }
        return user;
    }

    /**
     * Maps to User list
     * @param accountEntityList list of UserEntity
     * @return List<User>
     */
    @Override
    public List<User> mapToUserList(List<AccountEntity> accountEntityList) {
        List<User> userList = null;
        if (!CollectionUtils.isEmpty(accountEntityList)) {
            userList = new ArrayList<>();
            for (AccountEntity accountEntity : accountEntityList) {
                userList.add(mapToUser(accountEntity));
            }
        }
        return userList;
    }
}

