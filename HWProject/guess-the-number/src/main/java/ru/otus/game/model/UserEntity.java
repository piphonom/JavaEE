package ru.otus.game.model;

import javax.persistence.*;

@Entity
@Table(name = "User",
       uniqueConstraints = @UniqueConstraint(columnNames = {"name"})
)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int attempts;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }
}
