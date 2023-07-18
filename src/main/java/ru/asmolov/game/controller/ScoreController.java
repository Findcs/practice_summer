package ru.asmolov.game.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.asmolov.game.helper.ScoreRequest;
import ru.asmolov.game.model.User;
import ru.asmolov.game.service.AttemptService;
import ru.asmolov.game.service.UserService;

@Controller
@AllArgsConstructor
public class ScoreController {
    private AttemptService attemptService;
    private UserService userService;
    @PostMapping("/save")
    public ResponseEntity<String> saveScore(@RequestBody ScoreRequest scoreRequest) {
        String username = scoreRequest.getUsername();
        int score = scoreRequest.getScore();
        User user = userService.getUserByEmail(username);
        attemptService.saveAttempt(user,score);
        System.out.println(username + score);

        return ResponseEntity.ok("Score saved successfully.");
    }
}