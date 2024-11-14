package com.capgemini.wsb.fitnesstracker.training.internal;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.wsb.fitnesstracker.training.api.TrainingNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * REST controller for managing training sessions.
 * Provides endpoints to retrieve training sessions based on various criteria.
 */
@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
@Slf4j
class TrainingController {

    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;

    /**
     * Retrieves a training session by its ID.
     *
     * @param trainingId The ID of the training session to retrieve
     * @return An Optional containing the TrainingDto if found, or empty if not found
     * @throws TrainingNotFoundException if the training session is not found
     */
    @GetMapping("/training/{trainingId}")
    public Optional<TrainingDto> getTraining(@PathVariable Long trainingId) {
        try {
            return trainingService.getTraining(trainingId)
                    .map(trainingMapper::toDto);
        } catch (TrainingNotFoundException e) {
            throw e;
        }
    }

    /**
     * Retrieves all training sessions.
     *
     * @return A list of all TrainingDto objects
     */
    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.findAllTrainings()
                            .stream()
                            .map(trainingMapper::toDto)
                            .toList();
    /**
     * Creates a new training session.
     *
     * @param trainingDto TrainingDto object containing training details
     * @return A ResponseEntity containing the created Training object
     */
    @PostMapping
    public ResponseEntity<TrainingDto> createTraining(@RequestBody TrainingDto trainingDto) {
        return ResponseEntity.status(201)
                .body(trainingMapper.toDto(trainingService.createTraining(trainingMapper.toEntity(trainingDto))));
    }

    /**
    }

    /**
     * Retrieves training sessions for a specific user by their user ID.
     *
     * @param userId The ID of the user whose training sessions are to be retrieved
     * @return A list of TrainingDto objects for the specified user
     */
    @GetMapping("/{userId}")
    public List<TrainingDto> getTrainingsForUser(@PathVariable Long userId) {
        return trainingService.findTrainingsByUserId(userId)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Retrieves training sessions that ended after a specified timestamp.
     *
     * @param afterTime The timestamp after which the training sessions ended
     * @return A list of TrainingDto objects that ended after the specified timestamp
     */
    @GetMapping("/finished/{afterTime}")
    public List<TrainingDto> getFinishedTrainingsAfter(@PathVariable String afterTime) {
        return trainingService.findFinishedTrainingsAfter(afterTime)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Retrieves training sessions for a specific activity type.
     *
     * @param activityType The type of activity for which training sessions are to be retrieved
     * @return A list of TrainingDto objects for the specified activity type
     */
    @GetMapping("/activityType")
    public List<TrainingDto> getTrainingsByActivityType(@RequestParam ActivityType activityType) {
        return trainingService.findTrainingsByActivityType(activityType)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

}