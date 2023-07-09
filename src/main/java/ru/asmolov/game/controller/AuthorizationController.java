package ru.asmolov.game.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorizationController {
    @GetMapping("/")
    public String autho(){
        return "redirect:/profile";
    }
}
