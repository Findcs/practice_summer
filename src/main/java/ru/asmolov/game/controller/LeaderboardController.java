package ru.asmolov.game.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.asmolov.game.model.Attempt;
import ru.asmolov.game.model.User;
import ru.asmolov.game.repository.AttemptRepository;
import ru.asmolov.game.service.UserService;

import java.util.List;

@Controller
@AllArgsConstructor
public class LeaderboardController {
    private AttemptRepository attemptRepository;
    private UserService userService;

    @GetMapping("/leaderboard")
    public String showLeaderboard(Model model) {
        List<Attempt> topAttempts = attemptRepository.findTop10ByOrderByPointsDesc();
        List<User> users = userService.getTop10PlayersByMoney();
        model.addAttribute("users",users);
        model.addAttribute("topAttempts", topAttempts);
        return "leaderboard";
    }
}
