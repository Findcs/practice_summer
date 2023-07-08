package ru.asmolov.game.model;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "attempts")
@Getter
@Setter
public class Attempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private int points;
}
