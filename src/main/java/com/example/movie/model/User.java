package com.example.movie.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long id;

    @Column(name = "username")
    @Getter @Setter
    private String username;

    @Column(name = "email")
    @Getter @Setter
    private String email;

    @Column(name = "password")
    @Getter @Setter
    private String password;

    @Column(name = "type")
    @Getter @Setter
    private String type;


    public User() {

    }

    public User(String username, String email, String password, String type) {
        super();
        this.username = username;
        this.email = email;
        this.password = password;
        this.type = type;
    }
}