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
import ru.asmolov.game.service.AuthService;




@Controller
@AllArgsConstructor
public class LoginController {
    private AuthService authService;
    @GetMapping("/login")
    public String showLoginForm() {
        return "auth/login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        boolean flag = authService.authenticate(username,password);
        if (flag) {
            String sessionId = authService.createSessionId();
            Cookie sessionCookie = new Cookie("sessionId", sessionId);
            sessionCookie.setMaxAge(86400); // Установите срок действия cookie (здесь 1 день)
            sessionCookie.setPath("/"); // Установите путь, на котором будет доступна cookie
            response.addCookie(sessionCookie);
            // Сохраните идентификатор сеанса в качестве атрибута сеанса или в cookie
            // Редирект на защищенную страницу или выполните другую логику в зависимости от вашего приложения
            return "redirect:/profile";
        } else {
            // Ошибка аутентификации - добавляем сообщение об ошибке и перенаправляем обратно на страницу входа
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/login";
        }
    }
}