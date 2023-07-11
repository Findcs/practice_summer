package ru.asmolov.game.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.asmolov.game.model.Bird;
import ru.asmolov.game.model.User;
import ru.asmolov.game.repository.BirdRepository;
import ru.asmolov.game.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class BirdService {
    private final BirdRepository birdRepository;
    private UserRepository userRepository;

    public User addRed(User user){
        Bird red = birdRepository.findByName("red");
        user.getBirds().add(red);
        userRepository.save(user);
        return user;
    }

    public List<Bird> getAllBirds(){
        return birdRepository.findAll();
    }

    // Дополнительные методы для работы с птицами
}