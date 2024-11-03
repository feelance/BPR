package bpr.fitness.companion.backend.mapper.impl;

import bpr.fitness.companion.backend.mapper.UserMapper;
import bpr.fitness.companion.backend.model.dto.User;
import bpr.fitness.companion.backend.model.entity.ExerciseRecordEntity;
import bpr.fitness.companion.backend.model.entity.UserEntity;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapperImpl implements UserMapper {

    /**
     * Maps to UserEntity
     * @param user contains all user details
     * @return UserEntity
     */
    @Override
    public UserEntity mapToUserEntity(User user) {
        UserEntity userEntity = null;
        if (user != null) {
            userEntity = new UserEntity();
            userEntity.setId(user.getId());
            userEntity.setUserName(user.getUserName());
            userEntity.setPassword(user.getPassword());
        }
        return userEntity;
    }

    /**
     * Maps to User
     * @param userEntity UserEntity
     * @return User
     */
    @Override
    public User mapToUser(UserEntity userEntity) {
        User user = null;
        if (userEntity != null) {
            user = new User();
            user.setId(userEntity.getId());
            user.setUserName(userEntity.getUserName());
            user.setPassword(userEntity.getPassword());
        }
        return user;
    }

    /**
     * Maps to User list
     * @param userEntityList list of UserEntity
     * @return List<User>
     */
    @Override
    public List<User> mapToUserList(List<UserEntity> userEntityList) {
        List<User> userList = null;
        if (!CollectionUtils.isEmpty(userEntityList)) {
            userList = new ArrayList<>();
            for (UserEntity userEntity : userEntityList) {
                userList.add(mapToUser(userEntity));
            }
        }
        return userList;
    }
}

