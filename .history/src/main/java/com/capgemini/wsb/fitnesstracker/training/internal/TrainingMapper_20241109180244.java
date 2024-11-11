/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.capgemini.wsb.fitnesstracker.training.internal;

import org.springframework.stereotype.Component;

import com.capgemini.wsb.fitnesstracker.user.internal.UserDto;
import com.capgemini.wsb.fitnesstracker.user.internal.UserMapper;

/**
 *
 * @author oleko
 */
@Component
class TrainingMapper {
    
    UserMapper userMapper;

    TrainingDto toDto(Training training) {
        return new TrainingDto(
                training.getId(),
                new UserDto(),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed()
        );
    }


}
