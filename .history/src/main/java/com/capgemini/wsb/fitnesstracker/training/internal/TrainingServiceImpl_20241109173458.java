package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
class TrainingServiceImpl implements TrainingProvider, TrainingService {

    public final TrainingRepository trainingRepository;

    @Override
    public Optional<Training> getTraining(final Long trainingId) {
        throw new UnsupportedOperationException("Not finished yet");
    }

    @Override
    public List<Training> findAllTrainings() {
        throw new UnsupportedOperationException("Not finished yet");
    }

    @Override
    public List<Training> findTrainingsByUserId(Long userId) {
        throw new UnsupportedOperationException("Not finished yet");
    }

    @Override
    public List<Training> findTrainingsEndedAfter(Long timestamp) {
        throw new UnsupportedOperationException("Not finished yet");
    }

    @Override
    public List<Training> findTrainingsForActivity(ActivityType activityType) {
        throw new UnsupportedOperationException("Not finished yet");
    }
}