package com.capgemini.wsb.fitnesstracker.training;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class TrainingTestsObjectsGenerator {
    public static Training generateRandomTraining() {
        Date randomDate = generateRandomDate();
        Date endTime = new Date(randomDate.getTime() + (long) (Math.random() * 1000));

        Training training = new Training(generateRandomUser(), randomDate, endTime,
                ActivityType.values()[(int) (Math.random() * ActivityType.values().length)],
                Math.random() * 100, Math.random() * 100);
        return training;
    }

    public static User generateRandomUser() {
        /// Date to LocalDate
        Date randomDate = generateRandomDate();
        LocalDate birthdate = randomDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        User user = new User("John", "Doe", birthdate, "email@email.com");
        return user;
    }

    public static Date generateRandomDate() {
        Date randomDate = new Date((long) (Math.random() * Long.MAX_VALUE));
        return randomDate;
    }
}
