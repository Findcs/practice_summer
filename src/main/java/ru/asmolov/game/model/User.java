package ru.asmolov.game.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private int id;

    private String name;
    private String email;
    private String password;

    private int role;

    @OneToMany(mappedBy = "user")
    private List<Attempt> attempts;
}
