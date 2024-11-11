package com.capgemini.wsb.fitnesstracker.user.internal;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.annotation.Nullable;

/**
 * Data Transfer Object (DTO) for user information.
 * This record holds the user's unique identifier, first name, last name,
 * birthdate, and email address.
 *
 * @param Id The unique identifier of the user, can be null
 * @param firstName The first name of the user
 * @param lastName The last name of the user
 * @param birthdate The birthdate of the user, formatted as yyyy-MM-dd
 * @param email The email address of the user
 */
// Zminilismy dostępność rekordu na publiczny, aby móc go wykorzystywać w module training
public record UserDto(@Nullable Long Id, String firstName, String lastName,
        @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
        String email) {

    /**
     * Merges the current UserDto with an updated UserDto.
     * If a field in the updated UserDto is null, the value from the current UserDto is used.
     *
     * @param updatedUserDto The UserDto containing updated user details
     * @return A new UserDto instance with merged values
     */
    public UserDto mergeWith(UserDto updatedUserDto) {
        return new UserDto(Id(),
        takeValid(updatedUserDto.firstName(), firstName),
        takeValid(updatedUserDto.lastName(), lastName),
        takeValid(updatedUserDto.birthdate(), birthdate),
        takeValid(updatedUserDto.email(), email));
    }

    /**
     * Takes the first valid (non-null) value from the two provided options.
     *
     * @param <T> The type of the values
     * @param option1 The first option to consider
     * @param option2 The second option to consider
     * @return The first non-null value, or the second option if the first is null
     */
    private static <T> T takeValid(T option1, T option2) {
        return option1 == null ? option2 : option1;
    }
}