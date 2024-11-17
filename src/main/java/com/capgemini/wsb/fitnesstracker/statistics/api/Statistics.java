package com.capgemini.wsb.fitnesstracker.statistics.api;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import jakarta.persistence.*;
import lombok.*;

/**
 * Represents the statistics for a user's fitness activities.
 * This entity tracks the total number of trainings, distance covered, 
 * and calories burned by a user.
 */
@Entity
@Table(name = "statistics")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Statistics {

    /**
     * The unique identifier of the statistics entry.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The user associated with the fitness statistics.
     * This relationship indicates which user these statistics belong to.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * The total number of trainings the user has completed.
     */
    @Column(name = "total_trainings", nullable = false)
    private int totalTrainings;

    /**
     * The total distance covered by the user during their training sessions, 
     * in kilometers (or applicable unit).
     */
    @Column(name = "total_distance")
    private double totalDistance;

    /**
     * The total number of calories burned by the user during their trainings.
     */
    @Column(name = "total_calories_burned")
    private int totalCaloriesBurned;

    /**
     * Constructs a new {@link Statistics} object with the specified parameters.
     *
     * @param id The unique identifier of the statistics.
     * @param user The user associated with these statistics.
     * @param totalTrainings The total number of trainings completed.
     * @param totalDistance The total distance covered by the user in training.
     * @param totalCaloriesBurned The total calories burned by the user during training.
     */
    public Statistics(User user, int totalTrainings, double totalDistance, int totalCaloriesBurned) {
        this.user = user;
        this.totalTrainings = totalTrainings;
        this.totalDistance = totalDistance;
        this.totalCaloriesBurned = totalCaloriesBurned;
    }
}
