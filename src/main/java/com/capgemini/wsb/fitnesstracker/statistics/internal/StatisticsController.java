package com.capgemini.wsb.fitnesstracker.statistics.internal;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * REST controller for managing statistics in the fitness tracker application.
 */
@RestController
@RequestMapping("/v1/statistics")
@RequiredArgsConstructor
@Slf4j
class StatisticsController {

	private final StatisticsServiceImpl statisticsService;
	private final StatisticsMapper statisticsMapper;

	/**
	 * Retrieves all statistics.
	 *
	 * @return A list of all StatisticsDto objects.
	 */
	@GetMapping
	public List<StatisticsDto> getAllStatistics() {
		return statisticsService
				.findAllStatistics()
				.stream()
				.map(statisticsMapper::toDto)
				.toList();
	}

	/**
	 * Creates a new statistics entry.
	 *
	 * @param statisticsDto The DTO containing the statistics data to be created.
	 * @return The created StatisticsDto object.
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public StatisticsDto createStatistics(@RequestBody StatisticsDto statisticsDto) {
		return statisticsMapper.toDto(
				statisticsService.createStatistics(
						statisticsMapper.toEntity(statisticsDto)));
	}

	/**
	 * Updates an existing statistics entry.
	 *
	 * @param statisticsDto The DTO containing the updated statistics data.
	 * @param statisticsId  The ID of the statistics to be updated.
	 * @return The updated StatisticsDto object.
	 */
	@PutMapping("/{statisticsId}")
	public StatisticsDto updateStatistics(@RequestBody StatisticsDto statisticsDto, @PathVariable Long statisticsId) {
		Statistics statistics = statisticsMapper.toEntity(statisticsDto);
		statistics.setId(statisticsId);
		log.warn("updating as of statistics: " + statistics.toString());
		return statisticsMapper.toDto(statisticsService.updateStatistics(statistics));
	}

	/**
	 * Retrieves statistics details for a specific user.
	 *
	 * @param userId The ID of the user whose statistics are to be retrieved.
	 * @return A list of StatisticsDto objects for the given user.
	 */
	@GetMapping("/user/{userId}")
	public List<StatisticsDto> getStatisticsByUserId(@PathVariable Long userId) {
		return statisticsService
				.findStatisticsByUserId(userId)
				.stream()
				.map(statisticsMapper::toDto)
				.toList();
	}

	/**
	 * Deletes a statistics entry by its ID.
	 * Fetches the Statistics entity from the database using the given ID,
	 * and if it exists, deletes it.
	 *
	 * @param statisticsId The ID of the statistics entry to be deleted.
	 * @throws StatisticsNotFoundException if the statistics with the given ID is
	 *                                     not found
	 */
	@DeleteMapping("/{statisticsId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteStatistics(@PathVariable Long statisticsId) {
		statisticsService.deleteStatistics(
				statisticsService.getStatistics(statisticsId)
						.orElseThrow(() -> new StatisticsNotFoundException(statisticsId)));
	}

	/**
	 * Retrieves statistics where the total calories burned is greater than a
	 * specified value.
	 *
	 * @param totalCalories The minimum number of calories burned for statistics to
	 *                      be retrieved.
	 * @return A list of StatisticsDto objects with calories greater than the
	 *         specified value.
	 */
	@GetMapping("/calories/{totalCalories}")
	public List<StatisticsDto> findStatisticsByMinimumTotalCaloriesBurned(@PathVariable int totalCalories) {
		return statisticsService
				.findStatisticsByMinimumTotalCaloriesBurned(totalCalories)
				.stream()
				.map(statisticsMapper::toDto)
				.toList();
	}
}
