package com.scm.scm20.respository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.scm.scm20.entities.User;
/**
 * Using this interface for the following reasons :
 * 1. To write extra methods for DB related operation
 * 2. Custom Query Methods
 * 3. Custom Finder Methods
 */
// TODO : Add Is deleted column condition in find methods and update methods
// Todo: add method for login
@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    User findByUserName(String userName);
    User findByEmail(String email);
    User findByPhoneNumber(String phoneNumber);
    User findUserByUserNameOrEmail(String userName,String email);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.isDeleted = true WHERE u.userName = :userName AND u.email = :email AND u.phoneNumber = : phoneNumber")
    boolean deleteUser(String userName, String email, String phoneNumber);

}
