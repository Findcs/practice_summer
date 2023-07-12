package ru.asmolov.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.asmolov.game.model.Bird;
import ru.asmolov.game.model.User;

import java.util.Optional;

@Repository
public interface BirdRepository extends JpaRepository<Bird, Long> {
    // Дополнительные методы для работы с птицами
    Bird findByName(String name);
}