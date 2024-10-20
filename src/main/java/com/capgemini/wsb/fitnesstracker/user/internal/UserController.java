package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
@Slf4j
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/{Id}")
    public User getUser(@PathVariable Long Id) throws InterruptedException {
        return userService.getUser(Id).get();
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody UserDto userDto) throws InterruptedException {
        return ResponseEntity.status(201).body(userService.createUser(userMapper.toEntity(userDto)));
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody UserDto newUserDto) {
        UserDto oldEntityDto = userMapper.toDto(userService.getUser(userId).get());
        User updatedEntity = userMapper.toEntity(oldEntityDto.mergeWith(newUserDto));
        return userService.updateUser(updatedEntity.setId(userId));
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long Id) throws InterruptedException {
        userService.deleteUserById(Id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/email")
    public UserDto getUserByEmail(@RequestParam String email) {
        return userMapper.toDto(userService.getUserByEmail(email).get());
    }

    @GetMapping("/simple")
    public List<SimpleUserDto> getSimpleUsers() {
        return userService.findAllUsers()
                .stream()
                .map(user -> userMapper.toSimpleDto(user))
                .toList();
    }

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
