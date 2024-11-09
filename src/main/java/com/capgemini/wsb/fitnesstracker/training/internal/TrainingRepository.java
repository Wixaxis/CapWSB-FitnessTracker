package com.capgemini.wsb.fitnesstracker.training.internal;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.wsb.fitnesstracker.training.api.Training;

/**
 * Repository interface for managing Training entities.
 * Extends JpaRepository to provide CRUD operations and custom query methods.
 */
interface TrainingRepository extends JpaRepository<Training, Long> {

    /**
     * Finds all training sessions for a specific user by their user ID.
     *
     * @param userId The ID of the user whose training sessions are to be retrieved
     * @return A list of Training entities for the specified user
     */
    List<Training> findByUserId(Long userId);

    /**
     * Finds all training sessions that ended after a specified date.
     *
     * @param endTime The date after which the training sessions ended
     * @return A list of Training entities that ended after the specified date
     */
    List<Training> findByEndTimeAfter(Date endTime);

    /**
     * Finds all training sessions for a specific activity type.
     *
     * @param activityType The type of activity for which training sessions are to be retrieved
     * @return A list of Training entities for the specified activity type
     */
    List<Training> findByActivityType(ActivityType activityType);
}