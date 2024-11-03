package bpr.fitness.companion.backend.service;

import bpr.fitness.companion.backend.model.dto.User;

import java.util.List;

public interface UserService {
    /**
     * Create User
     * @param user contains all user details
     * @return User
     */
    User createUser(User user);

    /**
     * Get all users
     * @return List<User>
     */
    List<User> getAllUsers();

    /**
     * Get user by id
     * @param id of the user we want to get
     * @return User
     */
    User getUserById(Long id);

    /**
     * Update user
     * @param id of the user we want to update
     * @param userDetails contains all user details
     * @return User
     */
    User updateUser(Long id, User userDetails);

    /**
     * Delete user by id
     * @param id of the user we want to delete
     */
    void deleteUser(Long id);
}

