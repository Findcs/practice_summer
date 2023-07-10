package ru.asmolov.game.helper;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
@Setter
public class UsernameRequest {
    private String username;
    private String newusername;
}
