package ru.asmolov.game.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.asmolov.game.model.Session;
import ru.asmolov.game.model.User;
import ru.asmolov.game.repository.SessionRepository;
import ru.asmolov.game.service.AuthService;
import ru.asmolov.game.service.UserService;


@Controller
@AllArgsConstructor
public class LoginController {
    private AuthService authService;

    private UserService userService;
    private SessionRepository sessionRepository;
    @GetMapping("/login")
    public String showLoginForm() {
        return "auth/login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session, HttpServletResponse response) {
        if (authService.authenticate(username, password)) {
            String sessionId = authService.createSessionId();
            Cookie sessionCookie = new Cookie("sessionId", sessionId);
            sessionCookie.setMaxAge(86400); // Установите срок действия куки (здесь 1 день)
            sessionCookie.setPath("/"); // Установите путь, на котором будет доступна кука
            response.addCookie(sessionCookie);
            User user = userService.getUserByEmail(username); // Получить информацию о пользователе
            Session sessionObj = new Session();
            sessionObj.setSessionId(sessionId);
            sessionObj.setUser(user);
            sessionRepository.save(sessionObj);

            return "redirect:/profile";
        } else {
            return "redirect:/login?error";
        }
    }
}