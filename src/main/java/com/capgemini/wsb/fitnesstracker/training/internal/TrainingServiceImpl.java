package com.capgemini.wsb.fitnesstracker.training.internal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
class TrainingServiceImpl implements TrainingProvider, TrainingService {

    public final TrainingRepository trainingRepository;

    @Override
    public Optional<Training> getTraining(Long trainingId) {
        throw new UnsupportedOperationException("Not finished yet");
    }

    @Override
    public List<Training> findAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public List<Training> findTrainingsByUserId(Long userId) {
        return trainingRepository.findByUserId(userId);
    }

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


    @Override
    public List<Training> findTrainingsForActivity(ActivityType activityType) {
        throw new UnsupportedOperationException("Not finished yet");
    }

    @Override
    public Training createTraining(Training training) {
        throw new UnsupportedOperationException("Not finished yet");
    }

    @Override
    public Training updateTraining(Training training) {
        throw new UnsupportedOperationException("Not finished yet");
    }
    
}