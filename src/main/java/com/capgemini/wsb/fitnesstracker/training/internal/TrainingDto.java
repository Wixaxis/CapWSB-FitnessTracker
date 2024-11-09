package com.capgemini.wsb.fitnesstracker.training.internal;

import java.util.Date;

import com.capgemini.wsb.fitnesstracker.user.internal.UserDto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
class TrainingDto {
    private Long id;
    private UserDto user;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS+00:00", timezone = "UTC")
    private Date startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS+00:00", timezone = "UTC")
    private Date endTime;

    private ActivityType activityType;
    private double distance;
    private double averageSpeed;

    public TrainingDto(Long id, UserDto user, Date startTime, Date endTime, ActivityType activityType, double distance, double averageSpeed) {
        this.id = id;
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }


}
