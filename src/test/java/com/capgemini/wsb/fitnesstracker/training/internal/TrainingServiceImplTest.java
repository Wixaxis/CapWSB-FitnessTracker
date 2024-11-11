package com.capgemini.wsb.fitnesstracker.training.internal;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static com.capgemini.wsb.fitnesstracker.training.TrainingTestsObjectsGenerator.generateRandomTraining;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingNotFoundException;

import java.text.SimpleDateFormat;


class TrainingServiceImplTest {

    @Mock
    private TrainingRepository trainingRepository;

    @InjectMocks
    private TrainingServiceImpl trainingServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getTraining_returnsTraining_whenTrainingExists() {
        Long trainingId = 1L;
        Training training = generateRandomTraining();
        when(trainingRepository.findById(trainingId)).thenReturn(Optional.of(training));

        Optional<Training> result = trainingServiceImpl.getTraining(trainingId);

        assertTrue(result.isPresent());
        assertEquals(training, result.get());
    }

    @Test
    void getTraining_throwsTrainingNotFoundException_whenTrainingDoesNotExist() {
        Long trainingId = 1L;
        when(trainingRepository.findById(trainingId)).thenReturn(Optional.empty());

        assertThrows(TrainingNotFoundException.class, () -> trainingServiceImpl.getTraining(trainingId));
    }

    @Test
    void findAllTrainings_returnsAllTrainings() {
        List<Training> trainings = List.of(generateRandomTraining(), generateRandomTraining());
        when(trainingRepository.findAll()).thenReturn(trainings);

        List<Training> result = trainingServiceImpl.findAllTrainings();

        assertEquals(trainings, result);
    }

    @Test
    void findTrainingsByUserId_returnsTrainingsForUser() {
        Long userId = 1L;
        List<Training> trainings = List.of(generateRandomTraining(), generateRandomTraining());
        when(trainingRepository.findByUserId(userId)).thenReturn(trainings);

        List<Training> result = trainingServiceImpl.findTrainingsByUserId(userId);

        assertEquals(trainings, result);
    }

    @Test
    void findFinishedTrainingsAfter_returnsTrainingsAfterDate() throws ParseException {
        String afterTime = "2023-01-01";
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(afterTime);
        List<Training> trainings = List.of(generateRandomTraining(), generateRandomTraining());
        when(trainingRepository.findByEndTimeAfter(date)).thenReturn(trainings);

        List<Training> result = trainingServiceImpl.findFinishedTrainingsAfter(afterTime);

        assertEquals(trainings, result);
    }

    @Test
    void findFinishedTrainingsAfter_throwsIllegalArgumentException_whenDateFormatIsInvalid() {
        String afterTime = "invalid-date";

        assertThrows(IllegalArgumentException.class, () -> trainingServiceImpl.findFinishedTrainingsAfter(afterTime));
    }

    @Test
    void findTrainingsByActivityType_returnsTrainingsForActivityType() {
        ActivityType activityType = ActivityType.RUNNING;
        List<Training> trainings = List.of(generateRandomTraining(), generateRandomTraining());
        when(trainingRepository.findByActivityType(activityType)).thenReturn(trainings);

        List<Training> result = trainingServiceImpl.findTrainingsByActivityType(activityType);

        assertEquals(trainings, result);

    }
}