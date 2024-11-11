package com.capgemini.wsb.fitnesstracker.training.api;

import java.util.List;
import java.util.Optional;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;


public interface TrainingProvider {

    /**
     * Retrieves a training based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param trainingId id of the training to be searched
     * @return An {@link Optional} containing the located Training, or {@link Optional#empty()} if not found
     */
    Optional<Training> getTraining(Long trainingId);

    /**
     * Retrieves all training sessions.
     *
     * @return A list of all Training entities
     */
    List<Training> findAllTrainings();

    /**
     * Retrieves training sessions for a specific user based on their user ID.
     *
     * @param userId The ID of the user whose trainings are to be retrieved
     * @return A list of Training entities for the specified user
     */
    List<Training> findTrainingsByUserId(Long userId);

    /**
     * Retrieves training sessions that ended after a specified timestamp.
     *
     * @param timestamp The timestamp after which the trainings ended
     * @return A list of Training entities that ended after the specified timestamp
     */
    List<Training> findFinishedTrainingsAfter(String afterTime);

    /**
     * Retrieves training sessions for a specific activity type.
     *
     * @param activityType The type of activity for which trainings are to be retrieved
     * @return A list of Training entities for the specified activity type
     */
    List<Training> findTrainingsForActivity(ActivityType activityType);

}
