package ru.asmolov.game.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.asmolov.game.model.User;
import ru.asmolov.game.repository.UserRepository;


import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public List<User> getAllUsers()
    {
        return  userRepository.findAll();
    }

    public User getUserByEmail(String email){
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty())
            return null;
        else return userOptional.get();
    }

    public User add_user(String login,String password ) throws SQLException {
        User user = new User();
        user.setEmail(login);
        user.setPassword(password);
        user.setRole(1);
        if (userRepository.findByEmail(login).isEmpty()){
            userRepository.save(user);
            return user;}
        else return  null;
    }
}
