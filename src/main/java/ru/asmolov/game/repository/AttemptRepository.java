package ru.asmolov.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.asmolov.game.model.Attempt;

import java.util.List;


public interface AttemptRepository extends JpaRepository<Attempt, Integer> {
    // Дополнительные методы, если необходимо
    List<Attempt> findTop10ByUserIdOrderByPointsDesc(Integer userId);
    List<Attempt> findTop10ByOrderByPointsDesc ();
}