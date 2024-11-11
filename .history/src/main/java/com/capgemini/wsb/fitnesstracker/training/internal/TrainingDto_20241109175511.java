package com.capgemini.wsb.fitnesstracker.training.internal;

import java.util.Date;

import com.capgemini.wsb.fitnesstracker.user.internal.UserDto;

public class TrainingDto {
    private Long id;
    private UserDto user;
    private Date startTime;
    private Date endTime;
    private ActivityType activityType;
    private double distance;
    private double averageSpeed;

    public TrainingDto(Long id, UserDto user, Date startTime, Date endTime, ActivityType activityType, double distance, double averageSpeed) {
        this.id = id;
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }


}
