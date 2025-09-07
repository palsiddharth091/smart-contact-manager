package com.scm.scm20.service.implementation;

import java.util.List;
import java.util.Optional;

import com.scm.scm20.constants.Common;
import com.scm.scm20.constants.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.scm20.entities.User;
import com.scm.scm20.exception.ResourceNotFoundException;
import com.scm.scm20.respository.UserRepo;
import com.scm.scm20.service.UserService;

// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class UserServiceImpl implements UserService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    
    // Injecting beans of UserRepo
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    String[] ignoredProperties = {};

    @Override
    public Optional<User> findUserByEmail(String email) {
        User existingUser = userRepo.findByEmail(email);
        return Optional.ofNullable(existingUser);
    }
    @Override
    public Optional<User> findUserByUserName(String username) {
        User existingUser = userRepo.findByUserName(username);
        return Optional.ofNullable(existingUser);
    }
    @Override
    public Optional<User> findUserById(Long userId) {
        User existingUser = userRepo.findById(userId).orElse(null);
        return Optional.ofNullable(existingUser);
    }
    @Override
    public User saveUser(User user) {
        try {
            checkUserAlreadyPresent(user,false);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoleList(List.of(Common.USER_ROLE));
        return userRepo.save(user);
    }

    @Override
    public User updateUser(User existingUser) {
        // TODO: As of now username and email updation will not be allowed. We will allow it once we start handling session related changes. 
        // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // String currentUsername = authentication.getName(); Usually the username or email
       existingUser = userRepo.findByEmail(existingUser.getEmail());
       User updatedUser = new User();
       BeanUtils.copyProperties(existingUser, existingUser, ignoredProperties);
       return userRepo.save(updatedUser);
    }

    @Override
    public User checkUserAlreadyPresent(User user, boolean find) {
        User alreadyPresent = userRepo.findByEmail(user.getEmail());
        if (alreadyPresent != null) {
            LOGGER.info(Messages.USER_ALREADY_EXISTS_BY_EMAIL);
            if (!find) throw new ResourceNotFoundException(Messages.USER_ALREADY_EXISTS_BY_EMAIL);
            return alreadyPresent;
        }

        alreadyPresent = userRepo.findByUserName(user.getUsername());
        if (alreadyPresent != null) {
            LOGGER.info(Messages.USER_ALREADY_EXISTS_BY_USERNAME);
            if (!find) throw new ResourceNotFoundException(Messages.USER_ALREADY_EXISTS_BY_USERNAME);
            return alreadyPresent;
        }

        alreadyPresent = userRepo.findByPhoneNumber(user.getPhoneNumber());
        if (alreadyPresent != null) {
            LOGGER.info(Messages.USER_ALREADY_EXISTS_BY_PHONE_NUMBER);
            if (!find) throw new ResourceNotFoundException(Messages.USER_ALREADY_EXISTS_BY_PHONE_NUMBER);
            return alreadyPresent;
        }

        LOGGER.info("User is not present");
        return user;
    }

    @Override
    public boolean isUserExist(User user, boolean find) {
        user = checkUserAlreadyPresent(user, true);
        return (user!=null) ? true : false;
    }
    @Override
    public boolean deleteUser(User user) {
        boolean result = userRepo.deleteUser(user.getUsername(), user.getEmail(), user.getPhoneNumber());
        if(!result) throw new ResourceNotFoundException(Messages.ERROR_IN_DELETING);
        return result;
    }
    
    

    
    


}
