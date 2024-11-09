package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * REST controller for managing users in the fitness tracker application.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
@Slf4j
class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    /**
     * Retrieves all users.
     *
     * @return A list of UserDto objects representing all users
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param Id The unique identifier of the user
     * @return The User object representing the user
     * @throws InterruptedException if the operation is interrupted
     */
    @GetMapping("/{Id}")
    public User getUser(@PathVariable Long Id) throws InterruptedException {
        return userService.getUser(Id).get();
    }

    /**
     * Adds a new user.
     *
     * @param userDto The UserDto object containing user details
     * @return A ResponseEntity containing the created User object
     * @throws InterruptedException if the operation is interrupted
     */
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody UserDto userDto) throws InterruptedException {
        return ResponseEntity.status(201).body(userService.createUser(userMapper.toEntity(userDto)));
    }

    /**
     * Updates an existing user.
     *
     * @param userId The unique identifier of the user to update
     * @param newUserDto The UserDto object containing updated user details
     * @return The updated User object
     */
    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody UserDto newUserDto) {
        UserDto oldEntityDto = userMapper.toDto(userService.getUser(userId).get());
        User updatedEntity = userMapper.toEntity(oldEntityDto.mergeWith(newUserDto));
        return userService.updateUser(updatedEntity.setId(userId));
    }

    /**
     * Deletes a user by their unique identifier.
     *
     * @param Id The unique identifier of the user to delete
     * @return A ResponseEntity with no content
     * @throws InterruptedException if the operation is interrupted
     */
    @DeleteMapping("/{Id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long Id) throws InterruptedException {
        userService.deleteUserById(Id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves a user by their email.
     *
     * @param email The email of the user to retrieve
     * @return The UserDto object representing the user
     */
    @GetMapping("/email")
    public UserDto getUserByEmail(@RequestParam String email) {
        return userMapper.toDto(userService.getUserByEmail(email).get());
    }

    /**
     * Retrieves users whose email contains the specified fragment.
     *
     * @param emailFragment The fragment of the email to search for
     * @return A list of EmailUserDto objects representing the users
     */
    @GetMapping("/email/fragment")
    public List<EmailUserDto> getUsersByEmailFragment(@RequestParam String emailFragment) {
        return userService.findUsersByEmailFragment(emailFragment)
                .stream()
                .map(userMapper::toEmailUserDto)
                .toList();
    }

    /**
     * Retrieves simple user information.
     *
     * @return A list of SimpleUserDto objects representing the users
     */
    @GetMapping("/simple")
    public List<SimpleUserDto> getSimpleUsers() {
        return userService.findAllUsers()
                .stream()
                .map(user -> userMapper.toSimpleDto(user))
                .toList();
    }

    /**
     * Retrieves users older than the specified date.
     *
     * @param time The date to compare against
     * @return A list of UserDto objects representing the users
     */
    @GetMapping("/older/{time}")
    public List<UserDto> getOlderUsers(@PathVariable LocalDate time) {
        List<UserDto> users = userService.findAllUsers()
                .stream()
                .filter(user -> user.getBirthdate().isAfter(time))
                .map(userMapper::toDto)
                .toList();
        return users;
    }
}