package com.scm.scm20.service.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.scm.scm20.constants.Messages;
import com.scm.scm20.entities.User;
import com.scm.scm20.respository.UserRepo;
/**
 * Custom Class for implementing the UserDetailService the way we want to. 
 */
@Service
public class SecurityCustomUserDetailService implements UserDetailsService{

    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(username);
        return Optional.ofNullable(user).orElseThrow(()-> new UsernameNotFoundException(Messages.USER_NOT_FOUND_WITH_USERNAME));
    }
    
}
