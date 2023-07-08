package ru.asmolov.game.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.asmolov.game.model.Session;
import ru.asmolov.game.model.User;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, String> {

    Session findBySessionId(String sessionId);
    void deleteBySessionId(String sessionId);

}
