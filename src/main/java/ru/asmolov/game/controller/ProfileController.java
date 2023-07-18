package ru.asmolov.game.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.asmolov.game.helper.UsernameRequest;
import ru.asmolov.game.model.Attempt;
import ru.asmolov.game.model.Session;
import ru.asmolov.game.model.User;
import ru.asmolov.game.model.Bird;
import ru.asmolov.game.repository.AttemptRepository;
import ru.asmolov.game.repository.SessionRepository;
import ru.asmolov.game.service.BirdService;
import ru.asmolov.game.service.UserService;

import java.util.List;
import java.util.Random;
import java.util.Set;

@Controller
@AllArgsConstructor
public class ProfileController {

    private AttemptRepository attemptRepository;
    private SessionRepository sessionRepository;
    private UserService userService;
    private BirdService birdService;

    @GetMapping("/profile")
    public String showProfilePage(@RequestParam(name = "timestamp", required = false) Integer timestamp,HttpServletRequest request, Model model) {
        String sessionId = getSessionIdFromCookie(request);
        if (sessionId != null) {
            Session session = sessionRepository.findBySessionId(sessionId);
            if (session != null) {
                User user = session.getUser();
                Set<Bird> userBirds = user.getBirds();
                List<Attempt> attempts = attemptRepository.findTop10ByUserIdOrderByPointsDesc(user.getId());
                model.addAttribute("attempts", attempts);
                model.addAttribute("user", user);
                model.addAttribute("birds", userBirds);
                return "profile";
            }
        }

        return "redirect:/login";
    }


    @PostMapping("/profile/change-nickname")
    public String saveScore(@RequestBody UsernameRequest usernameRequest, RedirectAttributes redirectAttributes, HttpServletResponse response) {
        String username = usernameRequest.getUsername();
        System.out.println(username);
        String newusername = usernameRequest.getNewusername();
        System.out.println(newusername);
        User user = userService.getUserByEmail(username);
        if (userService.getUserByEmail(newusername)==null) {
            userService.setNewUsername(user,newusername);
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");
            int randomNumber = new Random().nextInt(10000);
            redirectAttributes.addAttribute("timestamp", randomNumber);
        }
        return "redirect:/profile";
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