package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service implementation for managing users in the fitness tracker application.
 * This class provides methods to create, update, delete, and retrieve users.
 */
@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    /**
     * Creates a new user.
     *
     * @param user The User entity to create
     * @return The created User entity
     * @throws IllegalArgumentException if the user already has an ID
     */
    @Override
    public User createUser(final User user) {
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    /**
     * Updates an existing user.
     *
     * @param user The User entity to update
     * @return The updated User entity
     */
    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Deletes a user by their unique identifier.
     *
     * @param Id The unique identifier of the user to delete
     */
    @Override
    public void deleteUserById(Long Id) {
        userRepository.deleteById(Id);
    }

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param userId The unique identifier of the user to retrieve
     * @return An Optional containing the found User or Optional.empty() if no user matched
     */
    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    /**
     * Retrieves a user by their email address.
     *
     * @param email The email address of the user to retrieve
     * @return An Optional containing the found User or Optional.empty() if no user matched
     */
    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Finds users whose email contains the specified fragment, ignoring case.
     *
     * @param emailFragment The fragment of the email to search for
     * @return A list of User entities matching the email fragment
     */
    @Override
    public List<User> findUsersByEmailFragment(String emailFragment) {
        return userRepository.findByEmailContainingIgnoreCase(emailFragment);
    }

    /**
     * Retrieves all users.
     *
     * @return A list of all User entities
     */
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}