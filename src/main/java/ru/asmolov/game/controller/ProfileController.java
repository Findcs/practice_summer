package ru.asmolov.game.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.asmolov.game.model.Session;
import ru.asmolov.game.model.User;
import ru.asmolov.game.repository.SessionRepository;
import ru.asmolov.game.service.UserService;

import java.security.Principal;
import java.util.Optional;

@Controller
public class ProfileController {

    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping("/profile")
    public String showProfilePage(HttpServletRequest request, Model model) {
        String sessionId = getSessionIdFromCookie(request);
        if (sessionId != null) {
            Session session = sessionRepository.findBySessionId(sessionId);
            if (session != null) {
                User user = session.getUser();
                model.addAttribute("user", user);
                return "profile";
            }
        }

        return "redirect:/login";
    }

    private String getSessionIdFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("sessionId".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}