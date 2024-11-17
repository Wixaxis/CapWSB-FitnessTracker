package com.capgemini.wsb.fitnesstracker.statistics.internal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;

/**
 * Repository interface for managing Statistics utilities.
 * Extends JpaRepository to provide CRUD operations and custom query methods.
 */
interface StatisticsRepository extends JpaRepository<Statistics, Long> {

        /**
         * Finds all statistics for a specific user by their user ID.
         *
         * @param userId The ID of the user whose statistics are to be retrieved
         * @return A list of Statistics entities for the specified user
         */
        List<Statistics> findByUserId(Long userId);

        /**
         * Finds all Statistics with total calories burned higher than given amount
         *
         * @param totalCaloriesBurnedMin The minimum amount of calories burned for
         *                               statistics to get included in the list
         * @return A list of Statistics entities with total burned calories higher than
         *         given
         */
        List<Statistics> findByTotalCaloriesBurnedGreaterThan(int totalCaloriesBurnedMin);
}
