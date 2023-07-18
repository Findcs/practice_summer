package ru.asmolov.game.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.asmolov.game.model.Attempt;
import ru.asmolov.game.model.Session;
import ru.asmolov.game.model.User;
import ru.asmolov.game.helper.CookieFinder;
import ru.asmolov.game.repository.AttemptRepository;
import ru.asmolov.game.repository.SessionRepository;

import java.util.List;

@Controller
@AllArgsConstructor
public class GameController {
    SessionRepository sessionRepository;
    AttemptRepository attemptRepository;
    @GetMapping("/game")
    public String showGamePage(HttpServletRequest request, Model model) {
        String sessionId = CookieFinder.getSessionIdFromCookie(request);
        if (sessionId != null) {
            Session session = sessionRepository.findBySessionId(sessionId);
            if (session != null) {
                User user = session.getUser();
                List<Attempt> attempts = attemptRepository.findAll();
                model.addAttribute("user", user);
                return "game";
            }
        }

        return "redirect:/login";
    }
}
