package ru.asmolov.game.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.asmolov.game.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findById(int userId);
    Optional<User> findByEmail(String email);
}
