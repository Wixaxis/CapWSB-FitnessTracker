/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.capgemini.wsb.fitnesstracker.training.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.internal.UserDto;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.internal.UserMapper;

/**
 * Component responsible for mapping Training entities to TrainingDto objects.
 * Utilizes UserMapper to map user-related data.
 * Utilizes UserProvider to retrieve user-related data.
 *
 * @author oleko
 */
@Component
class TrainingMapper {

    private final UserMapper userMapper;
    private final UserProvider userProvider;

    /**
     * Constructs a TrainingMapper with the specified UserMapper.
     *
     * @param userMapper The UserMapper to be used for mapping user data
     * @param userMapper   The UserMapper to be used for mapping user data
     * @param userProvider The UserProvider to be used for retrieving user data
     */
    @Autowired
    public TrainingMapper(UserMapper userMapper) {
    public TrainingMapper(UserMapper userMapper, UserProvider userProvider) {
        this.userMapper = userMapper;
        this.userProvider = userProvider;
    }

    /**
     * Maps a Training entity to a TrainingDto.
     *
     * @param training The Training entity to be mapped
     * @return The mapped TrainingDto object
     */
    public TrainingDto toDto(Training training){
        if (training == null) {
            return null;
        }
        return new TrainingDto(
                training.getId(),
                userMapper.toDto(training.getUser()),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed()
        );
    }
}