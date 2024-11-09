package com.capgemini.wsb.fitnesstracker.training.internal;

import java.util.Date;

import com.capgemini.wsb.fitnesstracker.user.internal.UserDto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for Training.
 * Represents the details of a training session.
 */
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

    /**
     * Constructor for TrainingDto.
     *
     * @param id The ID of the training session
     * @param user The user associated with the training session
     * @param startTime The start time of the training session
     * @param endTime The end time of the training session
     * @param activityType The type of activity for the training session
     * @param distance The distance covered in the training session
     * @param averageSpeed The average speed during the training session
     */
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