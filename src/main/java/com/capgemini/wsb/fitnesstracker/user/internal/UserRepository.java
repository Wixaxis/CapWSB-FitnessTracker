package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Repository interface for managing User entities.
 * Extends JpaRepository to provide CRUD operations and custom query methods.
 */
interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds users whose email contains the specified fragment, ignoring case.
     *
     * @param email The fragment of the email to search for
     * @return A list of User entities matching the email fragment
     */
    List<User> findByEmailContainingIgnoreCase(String email);

    /**
     * Finds a user by their exact email address.
     * This is a default method that searches through all users and matches by exact email.
     *
     * @param email The email address of the user to search for
     * @return An Optional containing the found User or Optional.empty() if no user matched
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                        .filter(user -> Objects.equals(user.getEmail(), email))
                        .findFirst();
    }
}