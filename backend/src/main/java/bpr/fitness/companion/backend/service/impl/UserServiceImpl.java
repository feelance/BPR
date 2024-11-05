package bpr.fitness.companion.backend.service.impl;

import bpr.fitness.companion.backend.exception.UserNotFoundException;
import bpr.fitness.companion.backend.mapper.UserMapper;
import bpr.fitness.companion.backend.model.dto.User;
import bpr.fitness.companion.backend.model.entity.AccountEntity;
import bpr.fitness.companion.backend.repository.UserRepository;
import bpr.fitness.companion.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public User createUser(User user) {
        AccountEntity accountEntity = mapper.mapToUserEntity(user);
        accountEntity = userRepository.save(accountEntity);
        return mapper.mapToUser(accountEntity);
    }

    @Override
    public List<User> getAllUsers() {
        List<AccountEntity> userEntities = userRepository.findAll();
        return mapper.mapToUserList(userEntities);
    }

    @Override
    public User getUserById(Long id) {
        Optional<AccountEntity> optUserEntity = userRepository.findById(id);
        return optUserEntity.map(mapper::mapToUser).orElse(null);
    }

    @Override
    public User updateUser(Long id, User userDetails) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
        userDetails.setId(id);
        AccountEntity accountEntity = mapper.mapToUserEntity(userDetails);
        userRepository.save(accountEntity);
        return mapper.mapToUser(accountEntity);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
        userRepository.deleteById(id);
    }
}

