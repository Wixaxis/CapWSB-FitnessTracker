package com.capgemini.wsb.fitnesstracker.statistics.api;

/**
 * Service interface for managing statistics.
 * Provides methods to create, delete and update statistics.
 */
public interface StatisticsService {

    /**
     * Creates a new statistics entry.
     *
     * @param statistics The Statistics entity to create
     * @return The created Statistics entity
     */
    Statistics createStatistics(Statistics statistics);

    /**
     * Updates an existing statistics entry.
     *
     * @param statistics The Statistics entity to update
     * @return The updated Statistics entity
     */
    Statistics updateStatistics(Statistics statistics);

    /**
     * Deletes a statistics entry.
     *
     * @param statistics The Statistics entity to delete.
     */
    void deleteStatistics(Statistics statistics);
}
