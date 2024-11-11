package com.capgemini.wsb.fitnesstracker.training.internal;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.wsb.fitnesstracker.training.api.Training;

interface TrainingRepository extends JpaRepository<Training, Long> {
    List <Training> findByUserId(Long userId);
    List <Training> findByEndTimeAfter(Date endTime);

}
