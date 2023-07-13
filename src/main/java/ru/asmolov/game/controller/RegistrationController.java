package ru.asmolov.game.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.asmolov.game.model.User;
import ru.asmolov.game.service.UserService;

import java.sql.SQLException;

@Controller
@AllArgsConstructor
public class RegistrationController {
    private UserService userService;
    @GetMapping("/registration")
    public String showRegistrationForm() {
        return "auth/Registration";
    }

    @PostMapping("/registration")
    public String registerUser(@RequestParam("username") String username, @RequestParam("password") String password, Model model) throws SQLException {
        // Используйте параметры username и password напрямую
        if(userService.add_user(username, password))
            return "redirect:/login";
        // Реализуйте сохранение пользователя в базе данных или другую логику регистрации
        model.addAttribute("error", "Юзернейм уже занят");
        return "auth/Registration";
    }
}