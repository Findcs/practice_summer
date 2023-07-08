package ru.asmolov.game.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.asmolov.game.repository.SessionRepository;

@Controller
@AllArgsConstructor
public class LogoutController {

    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping("/logout")
    @Transactional
    public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        // Удаление сессии и идентификатора сеанса
        session.invalidate();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("sessionId".equals(cookie.getName())) {
                    sessionRepository.deleteBySessionId( cookie.getValue());
                    cookie.setValue("");
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    break;
                }
            }

        }
        return "redirect:/login";
    }
}