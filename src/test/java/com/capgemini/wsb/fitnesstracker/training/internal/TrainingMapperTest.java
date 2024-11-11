package com.capgemini.wsb.fitnesstracker.training.internal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static com.capgemini.wsb.fitnesstracker.training.TrainingTestsObjectsGenerator.generateRandomTraining;
import static com.capgemini.wsb.fitnesstracker.training.TrainingTestsObjectsGenerator.generateRandomUser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.internal.UserDto;
import com.capgemini.wsb.fitnesstracker.user.internal.UserMapper;

import java.util.Date;

class TrainingMapperTest {

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private TrainingMapper trainingMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void toDto_mapsTrainingToTrainingDto() {
        Training training = generateRandomTraining();
        training.setId(1L);
        User user = generateRandomUser();
        training.setUser(user);
        training.setStartTime(new Date());
        training.setEndTime(new Date());
        training.setActivityType(ActivityType.RUNNING);
        training.setDistance(10.0);
        training.setAverageSpeed(5.0);

        UserDto userDto = new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getBirthdate(), user.getEmail());
        when(userMapper.toDto(user)).thenReturn(userDto);

        TrainingDto result = trainingMapper.toDto(training);

        assertEquals(training.getId(), result.getId());
        assertEquals(userDto, result.getUser());
        assertEquals(training.getStartTime(), result.getStartTime());
        assertEquals(training.getEndTime(), result.getEndTime());
        assertEquals(training.getActivityType(), result.getActivityType());
        assertEquals(training.getDistance(), result.getDistance());
        assertEquals(training.getAverageSpeed(), result.getAverageSpeed());
    }

    @Test
    void toDto_returnsNull_whenTrainingIsNull() {
        TrainingDto result = trainingMapper.toDto(null);

        assertNull(result);
    }

    @Test
    void toDto_returnsTrainingDtoWithNullUser_whenUserIsNull() {
        Training training = generateRandomTraining();
        training.setId(1L);
        training.setUser(null);
        training.setStartTime(new Date());
        training.setEndTime(new Date());
        training.setActivityType(ActivityType.RUNNING);
        training.setDistance(10.0);
        training.setAverageSpeed(5.0);

        TrainingDto result = trainingMapper.toDto(training);

        assertEquals(training.getId(), result.getId());
        assertNull(result.getUser());
        assertEquals(training.getStartTime(), result.getStartTime());
        assertEquals(training.getEndTime(), result.getEndTime());
        assertEquals(training.getActivityType(), result.getActivityType());
        assertEquals(training.getDistance(), result.getDistance());
        assertEquals(training.getAverageSpeed(), result.getAverageSpeed());
    }
}