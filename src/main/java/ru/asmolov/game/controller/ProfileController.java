package ru.asmolov.game.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.asmolov.game.model.User;
import ru.asmolov.game.service.UserService;

import java.security.Principal;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class ProfileController {
    private UserService userService;
    @GetMapping("/profile")
    public String showProfile(Model model, Principal principal) {
        String username = principal.getName(); // Получаем имя пользователя из Principal
        User user = userService.getUserByEmail(username);
        model.addAttribute("user", user);
        return "profile";
    }
}
