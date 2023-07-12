package ru.asmolov.game.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.asmolov.game.model.Bird;
import ru.asmolov.game.model.User;
import ru.asmolov.game.repository.BirdRepository;
import ru.asmolov.game.repository.UserRepository;


import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private BirdRepository birdRepository;

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
        user.setExp(0);
        user.setLvl(1);
        user.setMoney(0);
        user.setCurrentBird("default");
        Bird defbird = birdRepository.findByName("default");
        user.getBirds().add(defbird);
        if (userRepository.findByEmail(login).isEmpty()){
            userRepository.save(user);
            return user;}
        else return  null;
    }

    public void addExp(User user, int exp)
    {
        user.setMoney(user.getMoney() + exp/10);
        if(user.getExp() + exp> 100){
            user.setExp(user.getExp() + exp - 100);
            user.setLvl(user.getLvl()+1);
            userRepository.save(user);
        }
        else {
            user.setExp(user.getExp() + exp);
        }
    }
    public User setNewUsername(User user, String newUsername){
        user.setEmail(newUsername);
        userRepository.save(user);
        return user;
    }

    public List<User> getTop10PlayersByMoney() {
        return userRepository.findTop10ByOrderByMoneyDesc();
    }
}
