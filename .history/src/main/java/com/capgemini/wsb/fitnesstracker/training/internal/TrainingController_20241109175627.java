package com.capgemini.wsb.fitnesstracker.training.internal;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.wsb.fitnesstracker.user.internal.UserDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
@Slf4j
public class TrainingController {

    private final TrainingServiceImpl trainingService;

    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.findAllTrainings()
                            .stream()
                            .map(training -> new TrainingDto(
                                training.getId(),
                                new UserDto(training.getUser().getId(), training.getUser().getEmail()),
                                training.getStartTime(),
                                training.getEndTime(),
                                training.getDistance(),
                                training.getAverageSpeed()
                            ))
                            .collect(Collectors.toList());
    }

}
