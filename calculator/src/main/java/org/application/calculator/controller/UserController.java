package org.application.calculator.controller;

import org.application.calculator.dao.ConnectionToDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @Autowired
    private ConnectionToDB connectionToDB;


    @GetMapping("/home")
    public String initial(){
        return "home";
    }


}
