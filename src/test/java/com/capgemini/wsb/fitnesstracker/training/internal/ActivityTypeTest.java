package com.capgemini.wsb.fitnesstracker.training.internal;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ActivityTypeTest {

    @Test
    void getDisplayName_returnsCorrectDisplayName_forRunning() {
        ActivityType activityType = ActivityType.RUNNING;
        assertEquals("Running", activityType.getDisplayName());
    }

    @Test
    void getDisplayName_returnsCorrectDisplayName_forCycling() {
        ActivityType activityType = ActivityType.CYCLING;
        assertEquals("Cycling", activityType.getDisplayName());
    }

    @Test
    void getDisplayName_returnsCorrectDisplayName_forWalking() {
        ActivityType activityType = ActivityType.WALKING;
        assertEquals("Walking", activityType.getDisplayName());
    }

    @Test
    void getDisplayName_returnsCorrectDisplayName_forSwimming() {
        ActivityType activityType = ActivityType.SWIMMING;
        assertEquals("Swimming", activityType.getDisplayName());
    }

    @Test
    void getDisplayName_returnsCorrectDisplayName_forTennis() {
        ActivityType activityType = ActivityType.TENNIS;
        assertEquals("Tennis", activityType.getDisplayName());
    }
}