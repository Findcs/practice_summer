package ru.asmolov.game.controller;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.asmolov.game.helper.BuySkin;
import ru.asmolov.game.model.Attempt;
import ru.asmolov.game.model.Bird;
import ru.asmolov.game.model.Session;
import ru.asmolov.game.model.User;
import ru.asmolov.game.repository.SessionRepository;
import ru.asmolov.game.service.BirdService;
import ru.asmolov.game.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@AllArgsConstructor
public class BirdController {

    private BirdService birdService;
    private SessionRepository sessionRepository;

    private UserService userService;

    @GetMapping("/shop")
    public String showBirds(Model model, HttpServletRequest request) {
        String sessionId = getSessionIdFromCookie(request);
        if (sessionId != null) {
            Session session = sessionRepository.findBySessionId(sessionId);
            if (session != null) {
                User user = session.getUser();
                Set<Bird> userbirds = user.getBirds();
                List<Bird> birdList = new ArrayList<>(userbirds);
                List<Bird> birds = birdService.getAllBirds();
                model.addAttribute("birds", birds);
                model.addAttribute("birdList", birdList);
                return "shop";
            }
        }
        return "shop";
    }

    @PostMapping("/buy")
    public ResponseEntity buyBird(@RequestBody BuySkin buySkin, HttpServletRequest request){
        String sessionId = getSessionIdFromCookie(request);
        if (sessionId != null) {
            Session session = sessionRepository.findBySessionId(sessionId);
            if (session != null) {
                User user = session.getUser();
                String birdName = buySkin.getName();
                boolean flag = birdService.buy(user, birdName);
                if (flag)
                    return ResponseEntity.ok("Zaebis");
            }
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("/getuserbirds")
    public ResponseEntity<List<Bird>> getUserBirds(HttpServletRequest request){
        String sessionId = getSessionIdFromCookie(request);
        if (sessionId != null) {
            Session session = sessionRepository.findBySessionId(sessionId);
            if (session != null) {
                User user = session.getUser();
                List<Bird> userbirds = new ArrayList<>(user.getBirds());
                return ResponseEntity.ok().body(userbirds);
            }
        }
        return ResponseEntity.badRequest().build();
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