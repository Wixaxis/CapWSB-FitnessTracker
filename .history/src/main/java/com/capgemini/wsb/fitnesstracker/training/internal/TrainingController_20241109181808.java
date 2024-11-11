package com.capgemini.wsb.fitnesstracker.training.internal;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.wsb.fitnesstracker.user.internal.UserMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
@Slf4j
public class TrainingController {

    private final UserMapper userMapper;
    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;

    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.findAllTrainings()
                            .stream()
                            .map(trainingMapper::toDto)
                            .toList();
    }

}
