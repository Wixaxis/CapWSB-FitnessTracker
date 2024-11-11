package com.capgemini.wsb.fitnesstracker.training.internal;

public class TrainingDto {
    private Long id;
    private UserDto user;
    private Date startTime;
    private Date endTime;
    private double distance;
    private double averageSpeed;

    public TrainingDto(Long id, UserDto user, Date startTime, Date endTime, double distance, double averageSpeed) {
        this.id = id;
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }


}
