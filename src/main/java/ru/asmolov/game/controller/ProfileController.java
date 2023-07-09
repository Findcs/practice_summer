package ru.asmolov.game.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.asmolov.game.model.Attempt;
import ru.asmolov.game.model.Session;
import ru.asmolov.game.model.User;
import ru.asmolov.game.repository.AttemptRepository;
import ru.asmolov.game.repository.SessionRepository;
import ru.asmolov.game.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class ProfileController {

    private AttemptRepository attemptRepository;
    private SessionRepository sessionRepository;

    @GetMapping("/profile")
    public String showProfilePage(HttpServletRequest request, Model model) {
        String sessionId = getSessionIdFromCookie(request);
        if (sessionId != null) {
            Session session = sessionRepository.findBySessionId(sessionId);
            if (session != null) {
                User user = session.getUser();
                List<Attempt> attempts = attemptRepository.findTop10ByUserIdOrderByPointsDesc(user.getId());
                model.addAttribute("attempts", attempts);
                model.addAttribute("user", user);
                return "profile";
            }
        }

        return "redirect:/login";
    }

    private static String getSessionIdFromCookie(HttpServletRequest request) {
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