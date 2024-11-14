package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;

import org.springframework.stereotype.Component;

/**
 * Mapper component for converting between User entities and various User DTOs.
 */
// Zminilismy dostępność komponentu na publiczny, aby móc go wykorzystywać w module training
@Component
public class UserMapper {

    /**
     * Converts a User entity to a UserDto.
     *
     * @param user The User entity to convert
     * @return The corresponding UserDto
     */
    // Zminilismy dostępność obiektu na publiczny, aby móc go wykorzystywać w module training
    public UserDto toDto(User user) {
        return new UserDto(user.getId(),
                           user.getFirstName(),
                           user.getLastName(),
                           user.getBirthdate(),
                           user.getEmail());
    }

    /**
     * Converts a User entity to a SimpleUserDto.
     *
     * @param user The User entity to convert
     * @return The corresponding SimpleUserDto
     */
    SimpleUserDto toSimpleDto(User user) {
        return new SimpleUserDto(user.getId(), user.getFirstName(), user.getLastName());
    }

    /**
     * Converts a User entity to an EmailUserDto.
     *
     * @param user The User entity to convert
     * @return The corresponding EmailUserDto
     */
    EmailUserDto toEmailUserDto(User user) {
        return new EmailUserDto(user.getId(), user.getEmail());
    }

    /**
     * Converts a UserDto to a User entity.
     *
     * @param userDto The UserDto to convert
     * @return The corresponding User entity
     */
    public User toEntity(UserDto userDto) {
        return new User(
                        userDto.firstName(),
                        userDto.lastName(),
                        userDto.birthdate(),
                        userDto.email());
    }
}
