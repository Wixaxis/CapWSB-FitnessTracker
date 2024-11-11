package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.internal.UserApiIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserServiceImpl userService;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers_returnsListOfUserDtos() {
        List<User> users = List.of(new User(generateUser()), new User(generateUser()));
        List<UserDto> userDtos = List.of(new UserDto(null, "John", "Doe", LocalDate.now(), "john.doe@example.com"),
                                         new UserDto(null, "Jane", "Doe", LocalDate.now(), "jane.doe@example.com"));

        when(userService.findAllUsers()).thenReturn(users);
        when(userMapper.toDto(any(User.class))).thenReturn(userDtos.get(0), userDtos.get(1));

        List<UserDto> result = userController.getAllUsers();

        assertEquals(userDtos, result);
    }

    @Test
    void getUser_returnsUser() throws InterruptedException {
        User user = new User();
        when(userService.getUser(1L)).thenReturn(Optional.of(user));

        User result = userController.getUser(1L);

        assertEquals(user, result);
    }

    @Test
    void addUser_createsUser() throws InterruptedException {
        UserDto userDto = new UserDto(null, "John", "Doe", LocalDate.now(), "john.doe@example.com");
        User user = new User();
        when(userMapper.toEntity(userDto)).thenReturn(user);
        when(userService.createUser(user)).thenReturn(user);

        ResponseEntity<User> response = userController.addUser(userDto);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(user, response.getBody());
    }

    @Test
    void updateUser_updatesUser() {
        UserDto newUserDto = new UserDto(null, "John", "Doe", LocalDate.now(), "john.doe@example.com");
        UserDto oldUserDto = new UserDto(1L, "Jane", "Doe", LocalDate.now(), "jane.doe@example.com");
        User oldUser = new User();
        User updatedUser = new User();
        updatedUser.setId(1L);

        when(userService.getUser(1L)).thenReturn(Optional.of(oldUser));
        when(userMapper.toDto(oldUser)).thenReturn(oldUserDto);
        when(userMapper.toEntity(any(UserDto.class))).thenReturn(updatedUser);
        when(userService.updateUser(updatedUser)).thenReturn(updatedUser);

        User result = userController.updateUser(1L, newUserDto);

        assertEquals(updatedUser, result);
    }

    @Test
    void deleteUser_deletesUser() throws InterruptedException {
        doNothing().when(userService).deleteUserById(1L);

        ResponseEntity<Void> response = userController.deleteUser(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(userService, times(1)).deleteUserById(1L);
    }

    @Test
    void getUserByEmail_returnsUserDto() {
        User user = new User();
        UserDto userDto = new UserDto(null, "John", "Doe", LocalDate.now(), "john.doe@example.com");

        when(userService.getUserByEmail("john.doe@example.com")).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(userDto);

        UserDto result = userController.getUserByEmail("john.doe@example.com");

        assertEquals(userDto, result);
    }

    @Test
    void getUsersByEmailFragment_returnsListOfEmailUserDtos() {
        List<User> users = List.of(new User(), new User());
        List<EmailUserDto> emailUserDtos = List.of(new EmailUserDto(null, "john.doe@example.com"),
                                                   new EmailUserDto(null, "jane.doe@example.com"));

        when(userService.findUsersByEmailFragment("doe")).thenReturn(users);
        when(userMapper.toEmailUserDto(any(User.class))).thenReturn(emailUserDtos.get(0), emailUserDtos.get(1));

        List<EmailUserDto> result = userController.getUsersByEmailFragment("doe");

        assertEquals(emailUserDtos, result);
    }

    @Test
    void getSimpleUsers_returnsListOfSimpleUserDtos() {
        List<User> users = List.of(new User(), new User());
        List<SimpleUserDto> simpleUserDtos = List.of(new SimpleUserDto(null, "John", "Doe"),
                                                     new SimpleUserDto(null, "Jane", "Doe"));

        when(userService.findAllUsers()).thenReturn(users);
        when(userMapper.toSimpleDto(any(User.class))).thenReturn(simpleUserDtos.get(0), simpleUserDtos.get(1));

        List<SimpleUserDto> result = userController.getSimpleUsers();

        assertEquals(simpleUserDtos, result);
    }

    @Test
    void getOlderUsers_returnsListOfUserDtos() {
        List<User> users = List.of(new User(), new User());
        List<UserDto> userDtos = List.of(new UserDto(null, "John", "Doe", LocalDate.now(), "john.doe@example.com"),
                                         new UserDto(null, "Jane", "Doe", LocalDate.now(), "jane.doe@example.com"));

        when(userService.findAllUsers()).thenReturn(users);
        when(userMapper.toDto(any(User.class))).thenReturn(userDtos.get(0), userDtos.get(1));

        List<UserDto> result = userController.getOlderUsers(LocalDate.now().minusYears(1));

        assertEquals(userDtos, result);
    }

    public static User generateUser() {
        return new User(randomUUID().toString(), randomUUID().toString(), LocalDate.now(), randomUUID().toString());
    }
}