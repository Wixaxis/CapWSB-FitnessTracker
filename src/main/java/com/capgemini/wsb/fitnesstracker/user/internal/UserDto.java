package com.capgemini.wsb.fitnesstracker.user.internal;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.time.LocalDate;

record UserDto(@Nullable Long Id, String firstName, String lastName,
        @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
        String email) {
    public UserDto mergeWith(UserDto updatedUserDto) {
        return new UserDto(Id(),
        takeValid(updatedUserDto.firstName(), firstName),
        takeValid(updatedUserDto.lastName(), lastName),
        takeValid(updatedUserDto.birthdate(), birthdate),
        takeValid(updatedUserDto.email(), email));
    }

    private static <T> T takeValid(T option1, T option2) {
        return option1 == null ? option2 : option1;
    }
}
