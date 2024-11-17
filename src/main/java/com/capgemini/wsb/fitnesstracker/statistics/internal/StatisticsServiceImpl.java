package com.capgemini.wsb.fitnesstracker.statistics.internal;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsProvider;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Service implementation for managing fitness statistics.
 * Provides methods for creating, updating, and retrieving statistics.
 * This class integrates with the {@link StatisticsRepository} to perform
 * operations on statistics data.
 */
@Service
@RequiredArgsConstructor
@Slf4j
class StatisticsServiceImpl implements StatisticsProvider, StatisticsService {

        /**
         * The repository used for performing CRUD operations on {@link Statistics}
         * entities.
         */
        public final StatisticsRepository statisticsRepository;

        /**
         * Creates a new {@link Statistics} entry.
         *
         * @param statistics The {@link Statistics} entity to be created.
         * @return The created {@link Statistics} entity.
         */
        @Override
        public Statistics createStatistics(Statistics statistics) {
                return statisticsRepository.save(statistics);
        }

        /**
         * Updates an existing {@link Statistics} entry.
         *
         * @param statistics The {@link Statistics} entity to be updated.
         * @return The updated {@link Statistics} entity.
         */
        @Override
        public Statistics updateStatistics(Statistics statistics) {
                if (!statisticsRepository.existsById(statistics.getId())) {
                        throw new IllegalArgumentException(
                                        "Statistics entry with ID " + statistics.getId() + " not found.");
                }
                return statisticsRepository.save(statistics);
        }

        /**
         * Retrieves a {@link Statistics} entry based on its ID.
         *
         * @param statisticsId The ID of the statistics to be retrieved.
         * @return An {@link Optional} containing the {@link Statistics}, or an empty
         *         {@link Optional} if not found.
         */
        @Override
        public Optional<Statistics> getStatistics(Long statisticsId) {
                return statisticsRepository.findById(statisticsId);
        }

        /**
         * Retrieves all {@link Statistics} entries.
         *
         * @return A list of all {@link Statistics} entries.
         */
        @Override
        public List<Statistics> findAllStatistics() {
                return statisticsRepository.findAll();
        }

        /**
         * Retrieves a list of {@link Statistics} entries for a specific user,
         * identified by their user ID.
         *
         * @param userId The ID of the user whose statistics are to be retrieved.
         * @return A list of {@link Statistics} entries for the given user ID.
         */
        @Override
        public List<Statistics> findStatisticsByUserId(Long userId) {
                return statisticsRepository
                                .findAll()
                                .stream()
                                .filter(statistics -> statistics.getUser().getId().equals(userId))
                                .toList();
        }

        /**
         * Retrieves a list of {@link Statistics} entries for users who have burned at
         * least the given number of calories.
         *
         * @param totalCaloriesBurned The minimum number of calories burned for the
         *                            statistics to be retrieved.
         * @return A list of {@link Statistics} entries for users who have burned the
         *         specified minimum number of calories.
         */
        @Override
        public List<Statistics> findStatisticsByMinimumTotalCaloriesBurned(int totalCaloriesBurned) {
                return statisticsRepository
                                .findAll()
                                .stream()
                                .filter(statistics -> statistics.getTotalCaloriesBurned() >= totalCaloriesBurned)
                                .toList();
        }

        /**
         * Deletes a given statistics entry.
         * This method deletes the {@link Statistics} entity passed to it from
         * the database.
         *
         * @param statistics The {@link Statistics} entity to be deleted.
         */
        @Override
        public void deleteStatistics(Statistics statistics) {
                statisticsRepository.delete(statistics);
        }
}
