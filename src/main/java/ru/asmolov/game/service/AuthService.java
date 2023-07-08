package ru.asmolov.game.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.asmolov.game.model.User;
import ru.asmolov.game.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {
    private UserRepository userRepository;
    public boolean authenticate(String username, String password) {
        Optional<User> userOptional = userRepository.findByEmail(username);
        if (userOptional.isEmpty())
            return false;
        User user = userOptional.get();
        if (user.getPassword().equals(password)) {
            return true; // Аутентификация успешна
        }
        return false; // Неправильное имя пользователя или пароль
    }

    public String createSessionId() {
        // Реализуйте генерацию уникального идентификатора сеанса
        // и сохранение его, например, в базе данных или в памяти сервера
        String sessionId = generateUniqueId();
        // ... код сохранения идентификатора сеанса ...
        return sessionId;
    }

    private String generateUniqueId() {
        // Реализуйте генерацию уникального идентификатора сеанса
        // Например, можно использовать UUID.randomUUID().toString()
        return UUID.randomUUID().toString();
    }
}
