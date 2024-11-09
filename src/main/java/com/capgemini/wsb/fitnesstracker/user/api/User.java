package com.capgemini.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

/**
 * Represents a user entity in the fitness tracker application.
 * This class is mapped to the "users" table in the database.
 */
@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class User {

    /**
     * The unique identifier for the user.
     * This value is generated automatically.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    /**
     * The first name of the user.
     * This field is mandatory.
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /**
     * The last name of the user.
     * This field is mandatory.
     */
    @Column(name = "last_name", nullable = false)
    private String lastName;

    /**
     * The birthdate of the user.
     * This field is mandatory.
     */
    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    /**
     * The email of the user.
     * This field is mandatory and must be unique.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Constructs a new User instance with the specified details.
     *
     * @param firstName The first name of the user
     * @param lastName The last name of the user
     * @param birthdate The birthdate of the user
     * @param email The email of the user
     */
    public User(
            final String firstName,
            final String lastName,
            final LocalDate birthdate,
            final String email) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
    }

    /**
     * Sets the unique identifier for the user.
     *
     * @param id The unique identifier to set
     * @return The updated User instance
     */
    public User setId(Long id) {
        this.id = id;
        return this;
    }

}
