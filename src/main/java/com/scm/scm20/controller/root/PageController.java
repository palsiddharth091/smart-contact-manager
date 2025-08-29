package com.scm.scm20.controller.root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.scm.scm20.constants.Messages;
import com.scm.scm20.constants.PROVIDER;
import com.scm.scm20.entities.User;
import com.scm.scm20.entities.helper.Alert;
import com.scm.scm20.entities.helper.Severity;
import com.scm.scm20.forms.UserForm;
import com.scm.scm20.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class PageController {

     private static final Logger LOGGER = LoggerFactory.getLogger(PageController.class);

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

    // Method to render Signup Page

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

    // URL to Register User once the user has entered all the details. Redirection is happening from signup.html. Check the form attribute.

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute UserForm userForm ,BindingResult bindingResult ,HttpSession session) {
        LOGGER.info(userForm.toString());
        if(bindingResult.hasErrors()){
            return "root/signup/signup"; // Redirect to the URL
        }

        Alert alert = new Alert();
        if(!userForm.getPassword().equals(userForm.getConfirmPassword())){
            // Alert.builder().content(Messages.PASSWORD_MISMATCH).severity(Severity.yellow).build(); // TODO: Handle Password Mismatch
        }

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
        try {
            User savedUser = userService.saveUser(user);
            LOGGER.info("Saved User : "+savedUser.toString());
            alert = Alert.builder().content(Messages.REGISTRACTION_SUCCESSFUL).severity(Severity.green).build();
            LOGGER.info("User Saved Successfully");
        } catch (Exception e) {
            alert = Alert.builder().content(e.getMessage()).severity(Severity.red).build();
            LOGGER.error(e.getMessage());
        }
        session.setAttribute("alert", alert);
        return "redirect:/signup"; // Redirect to the URL
    }

}
