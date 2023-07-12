package ru.asmolov.game.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "birds")
@Getter
@Setter
public class Bird {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Другие поля птицы
    private String name;
    private int price;
    @ManyToMany(mappedBy = "birds")
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    // Конструкторы, геттеры, сеттеры и другие методы
}