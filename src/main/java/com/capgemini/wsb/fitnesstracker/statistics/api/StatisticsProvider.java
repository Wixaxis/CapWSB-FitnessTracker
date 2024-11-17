package com.capgemini.wsb.fitnesstracker.statistics.api;

import java.util.List;
import java.util.Optional;

/**
 * Interface for providing access to {@link Statistics} data.
 * This interface defines methods for retrieving statistics based on various criteria.
 */
public interface StatisticsProvider {

    /**
     * Retrieves the {@link Statistics} based on the provided ID.
     * If the statistics with the given ID is not found, an empty {@link Optional} will be returned.
     *
     * @param statisticsId The ID of the statistics to be retrieved.
     * @return An {@link Optional} containing the found {@link Statistics}, or {@link Optional#empty()} if no statistics is found with the given ID.
     */
    Optional<Statistics> getStatistics(Long statisticsId);

    /**
     * Retrieves all {@link Statistics} entries.
     *
     * @return A list of all {@link Statistics} records.
     */
    List<Statistics> findAllStatistics();

    /**
     * Retrieves a list of {@link Statistics} for a specific user, identified by the given user ID.
     *
     * @param userId The ID of the user whose statistics are to be retrieved.
     * @return A list of {@link Statistics} associated with the given user ID.
     */
    List<Statistics> findStatisticsByUserId(Long userId);

    /**
     * Retrieves a list of {@link Statistics} for users who have burned at least the given number of calories.
     *
     * @param totalCaloriesBurned The minimum number of calories burned for the statistics to be retrieved.
     * @return A list of {@link Statistics} for users who have burned the specified minimum number of calories.
     */
    List<Statistics> findStatisticsByMinimumTotalCaloriesBurned(int totalCaloriesBurned);
}
