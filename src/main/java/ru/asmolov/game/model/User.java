package ru.asmolov.game.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


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

    private int lvl;
    private int money;

    private int exp;

    private int role;
    @Column(columnDefinition = "VARCHAR(255) DEFAULT 'default'")
    private String currentBird;
    @OneToMany(mappedBy = "user")
    private List<Attempt> attempts;

    @ManyToMany
    @JoinTable(
            name = "user_bird",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "bird_id")
    )
    private Set<Bird> birds = new HashSet<>();
}
