package com.capgemini.wsb.fitnesstracker.statistics.internal;

import static java.time.LocalDate.now;
import static java.util.UUID.randomUUID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.wsb.fitnesstracker.IntegrationTest;
import com.capgemini.wsb.fitnesstracker.IntegrationTestBase;
import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import com.capgemini.wsb.fitnesstracker.user.api.User;

@IntegrationTest
@Transactional
@AutoConfigureMockMvc(addFilters = false)
class StatisticsApiIntegrationTest extends IntegrationTestBase {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private StatisticsRepository statisticsRepository;

	@Test
	void shouldReturnAllStatistics_whenGettingAllStatistics() throws Exception {

		User user1 = existingUser(generateClient());
		Statistics statistics1 = persistStatistics(generateStatistics(user1));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS+00:00");
		sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));

		mockMvc.perform(get("/v1/statistics").contentType(MediaType.APPLICATION_JSON))
				.andDo(log())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$[0].user.Id").value(user1.getId()))
				.andExpect(jsonPath("$[0].user.firstName").value(user1.getFirstName()))
				.andExpect(jsonPath("$[0].user.lastName").value(user1.getLastName()))
				.andExpect(jsonPath("$[0].user.email").value(user1.getEmail()))
				.andExpect(jsonPath("$[0].totalCaloriesBurned").value(statistics1.getTotalCaloriesBurned()))
				.andExpect(jsonPath("$[0].totalDistance").value(statistics1.getTotalDistance()))
				.andExpect(jsonPath("$[0].totalTrainings").value(statistics1.getTotalTrainings()))
				.andExpect(jsonPath("$[1]").doesNotExist());
	}

	@Test
	void shouldReturnStatisticsForDedicatedUser_whenGettingStatisticsForUser() throws Exception {

		User user1 = existingUser(generateClient());
		Statistics statistics1 = persistStatistics(generateStatistics(user1));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS+00:00");
		sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));

		mockMvc.perform(get("/v1/statistics/user/{userId}", user1.getId()).contentType(MediaType.APPLICATION_JSON))
				.andDo(log())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$[0].user.Id").value(user1.getId()))
				.andExpect(jsonPath("$[0].user.firstName").value(user1.getFirstName()))
				.andExpect(jsonPath("$[0].user.lastName").value(user1.getLastName()))
				.andExpect(jsonPath("$[0].user.email").value(user1.getEmail()))
				.andExpect(jsonPath("$[0].totalCaloriesBurned").value(statistics1.getTotalCaloriesBurned()))
				.andExpect(jsonPath("$[0].totalDistance").value(statistics1.getTotalDistance()))
				.andExpect(jsonPath("$[0].totalTrainings").value(statistics1.getTotalTrainings()))
				.andExpect(jsonPath("$[1]").doesNotExist());
	}

	@Test
	void shouldPersistStatistics_whenCreatingNewStatistics() throws Exception {

		User user1 = existingUser(generateClient());

		String requestBody = """
				{
				    "userId": "%s",
				    "totalCaloriesBurned": 1500,
				    "totalDistance": 35.5,
				    "totalTrainings": 10
				}
				""".formatted(user1.getId());

		mockMvc.perform(post("/v1/statistics").contentType(MediaType.APPLICATION_JSON).content(requestBody))
				.andDo(log())
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.user.Id").value(user1.getId()))
				.andExpect(jsonPath("$.user.firstName").value(user1.getFirstName()))
				.andExpect(jsonPath("$.user.lastName").value(user1.getLastName()))
				.andExpect(jsonPath("$.user.email").value(user1.getEmail()))
				.andExpect(jsonPath("$.totalCaloriesBurned").value(1500))
				.andExpect(jsonPath("$.totalDistance").value(35.5))
				.andExpect(jsonPath("$.totalTrainings").value(10));
	}

	@Test
	void shouldUpdateStatistics_whenUpdatingExistingStatistics() throws Exception {

		User user1 = existingUser(generateClient());
		Statistics statistics1 = persistStatistics(generateStatistics(user1));

		String requestBody = """
				{
				    "userId": "%s",
				    "totalCaloriesBurned": 2000,
				    "totalDistance": 45.0,
				    "totalTrainings": 15
				}
				""".formatted(user1.getId());

		mockMvc.perform(put("/v1/statistics/{statisticsId}", statistics1.getId())
				.contentType(MediaType.APPLICATION_JSON).content(requestBody))
				.andDo(log())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.user.Id").value(user1.getId()))
				.andExpect(jsonPath("$.user.firstName").value(user1.getFirstName()))
				.andExpect(jsonPath("$.user.lastName").value(user1.getLastName()))
				.andExpect(jsonPath("$.user.email").value(user1.getEmail()))
				.andExpect(jsonPath("$.totalCaloriesBurned").value(2000))
				.andExpect(jsonPath("$.totalDistance").value(45.0))
				.andExpect(jsonPath("$.totalTrainings").value(15));
	}

	@Test
	void shouldReturnStatisticsForMinimumCaloriesBurned_whenGettingStatisticsByMinimumCalories() throws Exception {
		User user1 = existingUser(generateClient());
		Statistics statistics1 = persistStatistics(generateStatistics(user1, 10, 35.5, 1500));
		Statistics statistics2 = persistStatistics(generateStatistics(user1, 5, 15.0, 500));

		mockMvc.perform(get("/v1/statistics/calories/{totalCalories}", 1000)
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(log())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$[0].user.Id").value(user1.getId()))
				.andExpect(jsonPath("$[0].totalCaloriesBurned").value(statistics1.getTotalCaloriesBurned()))
				.andExpect(jsonPath("$[0].totalDistance").value(statistics1.getTotalDistance()))
				.andExpect(jsonPath("$[0].totalTrainings").value(statistics1.getTotalTrainings()))
				.andExpect(jsonPath("$[1]").doesNotExist());
	}

	private static Statistics generateStatistics(User user) {
		return new Statistics(user, 1500, 35.5, 10);
	}

	private static Statistics generateStatistics(User user, int totalTrainings, double totalDistance,
			int totalCaloriesBurned ) {
		return new Statistics(user, totalTrainings, totalDistance, totalCaloriesBurned);
	}

	private Statistics persistStatistics(Statistics statistics) {
		return statisticsRepository.save(statistics);
	}

	private static User generateClient() {
		return new User(randomUUID().toString(), randomUUID().toString(), now(), randomUUID().toString());
	}

}
