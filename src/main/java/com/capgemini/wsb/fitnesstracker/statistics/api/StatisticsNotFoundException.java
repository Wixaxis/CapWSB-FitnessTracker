package com.capgemini.wsb.fitnesstracker.statistics.api;

import com.capgemini.wsb.fitnesstracker.exception.api.NotFoundException;

/**
 * Exception indicating that the {@link Statistics} entity was not found.
 */
@SuppressWarnings("squid:S110")
public class StatisticsNotFoundException extends NotFoundException {

    /**
     * Constructs a new StatisticsNotFoundException with the specified detail
     * message.
     *
     * @param message the detail message
     */
    private StatisticsNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new StatisticsNotFoundException with a message indicating the
     * statistics ID that was not found.
     *
     * @param id the ID of the statistics that was not found
     */
    public StatisticsNotFoundException(Long id) {
        this("Statistics with ID=%s was not found".formatted(id));
    }
}
