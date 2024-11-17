package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.user.internal.UserDto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for Statistics.
 * Represents the details of a statistics instance.
 */
@Data
class StatisticsDto {
    private Long id;
    private UserDto user;
    private Long userId;
    private int totalTrainings;
    private double totalDistance;
    private int totalCaloriesBurned;

    /**
     * Constructor for StatisticsDto.
     *
     * @param id The ID of the statistics
     * @param user The userDto associated with the statistics
     * @param userId The userId associated with user
     * @param totalTrainings The amount of total trainings
     * @param totalDistance The total distance covered by the user
     * @param totalCaloriesBurned The total amount of calories burned
     */
    public StatisticsDto(Long id, UserDto user, Long userId, int totalTrainings, double totalDistance, int totalCaloriesBurned) {
        this.id = id;
        this.user = user;
        this.userId = userId;
        this.totalTrainings = totalTrainings;
        this.totalDistance = totalDistance;
        this.totalCaloriesBurned = totalCaloriesBurned;
    }
}
