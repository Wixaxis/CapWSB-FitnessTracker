package com.capgemini.wsb.fitnesstracker.training.internal;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.wsb.fitnesstracker.training.api.TrainingNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
@Slf4j
class TrainingController {

    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;

    @GetMapping("/training/{trainingId}")
    public Optional <TrainingDto> getTraining(@PathVariable Long trainingId) {
        try {
            return trainingService.getTraining(trainingId)
                    .map(trainingMapper::toDto);
        } catch (TrainingNotFoundException e) {
            throw new TrainingNotFoundException(trainingId);
        }
        }

    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.findAllTrainings()
                            .stream()
                            .map(trainingMapper::toDto)
                            .toList();
    }

    @GetMapping("/{userId}")
    public List<TrainingDto> getTrainingsForUser(@PathVariable Long userId) {
        return trainingService.findTrainingsByUserId(userId)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("/finished/{afterTime}")
    public List<TrainingDto> getFinishedTrainingsAfter(@PathVariable String afterTime) {
        return trainingService.findFinishedTrainingsAfter(afterTime)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("/activityType")
    public List<TrainingDto> getTrainingsByActivityType(@RequestParam ActivityType activityType) {
        return trainingService.findTrainingsByActivityType(activityType)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

}
