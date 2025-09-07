package com.scm.scm20.controller.secured;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.scm20.constants.Common;


/**
 * Controller class for Protected routes related to user.
 */
@Controller
@RequestMapping("/user")
public class UserController {


    // TODO: User Dashboard  
    @GetMapping("/dashboard")
    public String dashboard() {
        return Common.USER_ROUTES+"/dashboard";
    }

    // TODO: Profile

    @GetMapping("/profile")
    public String profile() {
        return Common.USER_ROUTES+"/profile";
    }
    
    // TODO: User Add Contacts 
    // TODO: User View Contacts 
    // TODO: User Edit Contacts 
    // TODO: User Delete Contacts 
    // TODO: User Search contact

}
