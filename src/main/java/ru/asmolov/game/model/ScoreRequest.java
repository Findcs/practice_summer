package ru.asmolov.game.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
@Setter
public class ScoreRequest {
    private String username;
    private int score;
}
