package com.scm.scm20.controller.miscellaneous;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class MiscellaneousController {

@GetMapping("/about")
public String about() {
    return "about";
}

@GetMapping("/services")
public String services() {
    return "services";
}

    
}
