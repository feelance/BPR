package bpr.fitness.companion.backend.mapper;

import bpr.fitness.companion.backend.model.dto.User;
import bpr.fitness.companion.backend.model.entity.AccountEntity;

import java.util.List;

public interface UserMapper {

    /**
     * Maps to UserEntity
     * @param user contains all user details
     * @return UserEntity
     */
    AccountEntity mapToUserEntity(User user);

    /**
     * Maps to User list
     * @param accountEntityList list of UserEntity
     * @return List<User>
     */
    List<User> mapToUserList(List<AccountEntity> accountEntityList);

    /**
     * Maps to User
     * @param accountEntity UserEntity
     * @return User
     */
    User mapToUser(AccountEntity accountEntity);
}

