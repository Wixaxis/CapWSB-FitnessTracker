package com.capgemini.wsb.fitnesstracker.user.api;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

/**
 * Creates a new user in the system.
 *
 * @param user The user entity to be created
 * @return The created user entity
 */
User createUser(User user);

/**
 * Updates an existing user in the system.
 *
 * @param user The user entity with updated information
 * @return The updated user entity
 */
User updateUser(User user);

/**
 * Deletes a user from the system by their unique identifier.
 *
 * @param Id The unique identifier of the user to be deleted
 */
void deleteUserById(Long Id);

}
