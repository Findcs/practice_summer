package ru.asmolov.game.service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.asmolov.game.model.Attempt;
import ru.asmolov.game.model.User;
import ru.asmolov.game.repository.AttemptRepository;
import ru.asmolov.game.repository.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AttemptService {

    private AttemptRepository attemptRepository;
    private UserService userService;

    public void saveAttempt(User user, int score) {
        Attempt attempt = new Attempt();
       /* Optional<User> userOptional = userRepository.findByEmail(username);
        if (userOptional.isEmpty())
            return false;*/
        attempt.setUser(user);
        attempt.setPoints(score);
        userService.addExp(user,score);
        // Другие операции сохранения, если необходимо
        attemptRepository.save(attempt);
    }
}