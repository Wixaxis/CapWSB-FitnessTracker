package com.capgemini.wsb.fitnesstracker.training.api;



/**
 * Service interface for managing training sessions.
 * Provides methods to create and update training sessions.
 */
public interface TrainingService {

    /**
     * Creates a new training session.
     *
     * @param training The Training entity to create
     * @return The created Training entity
     */
    Training createTraining(Training training);

    /**
     * Updates an existing training session.
     *
     * @param training The Training entity to update
     * @return The updated Training entity
     */
    Training updateTraining(Training training);
}