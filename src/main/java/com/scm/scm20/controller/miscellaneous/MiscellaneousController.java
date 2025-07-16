package com.scm.scm20.controller.miscellaneous;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class MiscellaneousController {

@GetMapping("/about")
public String about(Model model) {
    model.addAttribute("title", "About");
    return "root/about/about";
}

@GetMapping("/services")
public String services() {
    return "root/services/services";
}

    
}
