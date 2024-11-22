package com.capgemini.wsb.fitnesstracker.statistics.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

/**
 * A scheduled job to send monthly training statistics emails to all users.
 */
@Component
@Slf4j
public class StatisticsMonthlyMailJob {

	private final StatisticsServiceImpl statisticsService;
	private final UserProvider userProvider;
	private final TrainingProvider trainingProvider;

	@Autowired
	public StatisticsMonthlyMailJob(StatisticsServiceImpl statisticsService, UserProvider userProvider,
			TrainingProvider trainingProvider) {
		this.statisticsService = statisticsService;
		this.userProvider = userProvider;
		this.trainingProvider = trainingProvider;
	}

	/**
	 * Scheduled task to generate and send monthly training reports via email.
	 * Runs on the 1st of every month at midnight.
	 */
	@Scheduled(cron = "0 0 0 1 * ?") // Runs at 00:00 on the 1st day of each month
	public void executeMonthlyMailJob() {
		log.info("Starting monthly mail job for training statistics...");
		LocalDate lastMonth = LocalDate.now().minusMonths(1);
		userProvider.findAllUsers().stream().forEach(user -> {
			List<Training> thisMonthTrainings = trainingProvider.findTrainingsByUserId(user.getId())
					.stream()
					.filter(training -> lastMonth.isBefore(
							training.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()))
					.toList();
			int totalTrainings = thisMonthTrainings.size();
			double totalDistance = thisMonthTrainings.stream().map(training -> training.getDistance()).mapToDouble(Double::doubleValue).sum();
			int totalCaloriesBurned = 0;
			Statistics statistics = new Statistics(user, totalTrainings, totalDistance, totalCaloriesBurned);

			// TODO: Send statistics through email


		});
	}
}
