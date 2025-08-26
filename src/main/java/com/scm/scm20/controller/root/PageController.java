package com.scm.scm20.controller.root;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.scm.scm20.constants.PROVIDER;
import com.scm.scm20.entities.User;
import com.scm.scm20.forms.UserForm;
import com.scm.scm20.service.UserService;

@Controller
public class PageController {

    @Autowired
    UserService userService;

    // About Page

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About");
        return "root/about/about";
    }

    // Services Page

    @GetMapping("/services")
    public String services(Model model) {
        model.addAttribute("title", "Services");
        return "root/services/services";
    }

    // Home Page

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("title", "Home");
        return "root/home";
    }

    // Contact Page

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("title", "Contact");
        return "root/contact/contact";
    }

    // Home Page

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("title", "Register");
        UserForm userform = new UserForm();
        model.addAttribute("userForm", userform);
        return "root/signup/signup";
    }

    // Login Page

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Login");
        return "root/login/login";
    }

    // URL to Register User

    @PostMapping("/register")
    public String register(@ModelAttribute UserForm userForm) {
        System.out.println(userForm.toString());

        User user = User.builder()
        .name(userForm.getName())
        .email(userForm.getEmail())
        .password(userForm.getPassword())
        .about(userForm.getAbout())
        .phoneNumber(userForm.getPhoneNumber())
        .profilePicture("hello") // TODO: Make it soft coded
        .userName(userForm.getUserName())
        .provider(PROVIDER.SELF) // TODO: Make it dynamic depending upon SSO or something else
        .build();
        // Validate data
        User savUser = userService.saveUser(user);
        System.out.println(savUser);
        return "redirect:/signup"; // Redirect to the URL
    }

}
