package com.capgemini.wsb.fitnesstracker.statistics.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.internal.UserDto;
import com.capgemini.wsb.fitnesstracker.user.internal.UserMapper;

/**
 * Component responsible for mapping {@link Statistics} entities to
 * {@link StatisticsDto} objects and vice versa.
 *
 * This class handles the conversion between data models used in the application
 * layer and the persistence layer.
 * 
 * It utilizes {@link UserMapper} for user-related mapping and optionally
 * retrieves
 * {@link User} entities through {@link UserProvider}.
 * 
 * @author wixaxis
 */
@Component
class StatisticsMapper {

        /**
         * Used for mapping between {@link User} entities and {@link UserDto} objects.
         */
        private final UserMapper userMapper;

        /**
         * Used to fetch {@link User} entities when needed.
         */
        private final UserProvider userProvider;

        /**
         * Constructs a {@link StatisticsMapper} with the specified dependencies.
         * 
         * @param userMapper   The {@link UserMapper} instance used for user mapping.
         * @param userProvider The {@link UserProvider} instance used to retrieve
         *                     {@link User} entities.
         */
        @Autowired
        public StatisticsMapper(UserMapper userMapper, UserProvider userProvider) {
                this.userMapper = userMapper;
                this.userProvider = userProvider;
        }

        /**
         * Converts a {@link Statistics} entity into a {@link StatisticsDto}.
         *
         * This method extracts data from a {@link Statistics} entity and maps it to
         * a {@link StatisticsDto} for use in the application layer.
         *
         * @param statistics The {@link Statistics} entity to be converted.
         * @return A {@link StatisticsDto} representing the data in the given
         *         {@link Statistics} entity, or null if the input is null.
         */
        public StatisticsDto toDto(Statistics statistics) {
                if (statistics == null)
                        return null;
                return new StatisticsDto(
                                statistics.getId(),
                                userMapper.toDto(statistics.getUser()),
                                (statistics.getUser() != null) ? statistics.getUser().getId() : null,
                                statistics.getTotalTrainings(),
                                statistics.getTotalDistance(),
                                statistics.getTotalCaloriesBurned());
        }

        /**
         * Converts a {@link StatisticsDto} to a {@link Statistics} entity.
         *
         * This method transforms data from a {@link StatisticsDto} into a
         * {@link Statistics}
         * entity for database operations. If the {@link UserDto} is not provided,
         * it fetches the {@link User} entity based on the user ID.
         * 
         * @param statisticsDto The {@link StatisticsDto} to be converted.
         *                      Must not be null.
         * @return A {@link Statistics} entity containing the data from the
         *         {@link StatisticsDto}.
         */
        Statistics toEntity(StatisticsDto statisticsDto) {
                User user = statisticsDto.getUser() != null
                                ? userMapper.toEntity(statisticsDto.getUser())
                                : userProvider.getUser(statisticsDto.getUserId()).orElseThrow();

                return new Statistics(
                                user,
                                statisticsDto.getTotalTrainings(),
                                statisticsDto.getTotalDistance(),
                                statisticsDto.getTotalCaloriesBurned());
        }
}
