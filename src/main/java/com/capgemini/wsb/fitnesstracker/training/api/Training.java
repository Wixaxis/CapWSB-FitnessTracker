package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

/**
 * Entity representing a training session.
 * Contains information about the user, start and end times, activity type, distance, and average speed.
 */
@Entity
@Table(name = "trainings")
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Training {

    /**
     * Unique identifier for the training session.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The user associated with the training session.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * The start time of the training session.
     */
    @Column(name = "start_time", nullable = false)
    private Date startTime;

    /**
     * The end time of the training session.
     */
    @Column(name = "end_time", nullable = false)
    private Date endTime;

    /**
     * The type of activity performed during the training session.
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "activity_type", nullable = false)
    private ActivityType activityType;

    /**
     * The distance covered during the training session.
     */
    @Column(name = "distance")
    private double distance;

    /**
     * The average speed during the training session.
     */
    @Column(name = "average_speed")
    private double averageSpeed;

    /**
     * Constructs a new Training instance with the specified details.
     *
     * @param user The user associated with the training session
     * @param startTime The start time of the training session
     * @param endTime The end time of the training session
     * @param activityType The type of activity performed during the training session
     * @param distance The distance covered during the training session
     * @param averageSpeed The average speed during the training session
     */
    public Training(
            final User user,
            final Date startTime,
            final Date endTime,
            final ActivityType activityType,
            final double distance,
            final double averageSpeed) {
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }
}