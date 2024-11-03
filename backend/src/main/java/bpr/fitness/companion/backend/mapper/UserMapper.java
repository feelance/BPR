package bpr.fitness.companion.backend.mapper;

import bpr.fitness.companion.backend.model.dto.User;
import bpr.fitness.companion.backend.model.entity.UserEntity;

import java.util.List;

public interface UserMapper {

    /**
     * Maps to UserEntity
     * @param user contains all user details
     * @return UserEntity
     */
    UserEntity mapToUserEntity(User user);

    /**
     * Maps to User list
     * @param userEntityList list of UserEntity
     * @return List<User>
     */
    List<User> mapToUserList(List<UserEntity> userEntityList);

    /**
     * Maps to User
     * @param userEntity UserEntity
     * @return User
     */
    User mapToUser(UserEntity userEntity);
}

