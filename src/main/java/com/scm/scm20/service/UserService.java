package com.scm.scm20.service;

import java.util.List;
import java.util.Optional;

import com.scm.scm20.entities.User;

/**
 * Using this interface to write business logic for user management
 */
// Todo: add method for login
public interface UserService {

    // Read
    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByUserName(String username);
    
    Optional<User> findUserById(Long userId);
    
    /**
     * This method checks if a user with a particular username, email or phone is
     * already present or not.
     * 
     * @param user
     * @param find
     * @return User object if it is already present, if find flag is true else throws
     *         exception if the user is not present. Finding happens based on email,
     *         username or phone number
     */
    User checkUserAlreadyPresent(User user, boolean find);

    /**
     * Check if the user is present or not. 
     * @param user
     * @param find
     * @return
     */
    boolean isUserExist(User user, boolean find);
    
    // Create
    User saveUser(User user);
    
    // Update
    /**
     * Method to update details of the user.
     * 
     * @param user
     * @return User object if the user is updated successfully
     */
    User updateUser(User user);

    // Delete
    /**
     * Method to delete the user
     * @param user
     * @return true if the user is deleted else it returns false.
     */
    boolean deleteUser(User user);

}
