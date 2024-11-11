package com.capgemini.wsb.fitnesstracker.training.internal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingNotFoundException;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Service implementation for managing training sessions.
 * Provides methods to retrieve, create, and update training sessions.
 */
@Service
@RequiredArgsConstructor
@Slf4j
class TrainingServiceImpl implements TrainingProvider, TrainingService {

    public final TrainingRepository trainingRepository;

    /**
     * Retrieves a training session by its ID.
     *
     * @param trainingId The ID of the training session to retrieve
     * @return An Optional containing the Training if found, or empty if not found
     * @throws TrainingNotFoundException if the training session is not found
     */
    @Override
    public Optional<Training> getTraining(Long trainingId) {
        if (trainingRepository.findById(trainingId).isEmpty()) {
            throw new TrainingNotFoundException(trainingId);
        }
        return trainingRepository.findById(trainingId);
    }

    /**
     * Retrieves all training sessions.
     *
     * @return A list of all Training entities
     */
    @Override
    public List<Training> findAllTrainings() {
        return trainingRepository.findAll();
    }

    /**
     * Retrieves training sessions for a specific user by their user ID.
     *
     * @param userId The ID of the user whose training sessions are to be retrieved
     * @return A list of Training entities for the specified user
     */
    @Override
    public List<Training> findTrainingsByUserId(Long userId) {
        return trainingRepository.findByUserId(userId);
    }

    /**
     * Retrieves training sessions that ended after a specified timestamp.
     *
     * @param afterTime The timestamp after which the training sessions ended
     * @return A list of Training entities that ended after the specified timestamp
     * @throws IllegalArgumentException if the date format is invalid
     */
    @Override
    public List<Training> findFinishedTrainingsAfter(String afterTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = sdf.parse(afterTime);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + afterTime);
        }
        return trainingRepository.findByEndTimeAfter(date);
    }

    /**
     * Retrieves training sessions for a specific activity type.
     *
     * @param activityType The type of activity for which training sessions are to be retrieved
     * @return A list of Training entities for the specified activity type
     */
    @Override
    public List<Training> findTrainingsByActivityType(ActivityType activityType) {
        return trainingRepository.findByActivityType(activityType);
    }

    /**
     * Creates a new training session.
     *
     * @param training The Training entity to be created
     * @return The created Training entity
     * @throws UnsupportedOperationException if the method is not implemented
     */
    @Override
    public Training createTraining(Training training) {
        throw new UnsupportedOperationException("Not finished yet");
    }

    /**
     * Updates an existing training session.
     *
     * @param training The Training entity to be updated
     * @return The updated Training entity
     * @throws UnsupportedOperationException if the method is not implemented
     */
    @Override
    public Training updateTraining(Training training) {
        throw new UnsupportedOperationException("Not finished yet");
    }

}