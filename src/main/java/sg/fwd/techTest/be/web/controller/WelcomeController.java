package sg.fwd.techTest.be.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/*
Author : Gilang Permadi Khasani
Description : For run when empty URI
Version : 0.1
Last Update : 17-05-2021
 */
@Controller
public class WelcomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        return "welcome";
    }

}
