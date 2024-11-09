package com.capgemini.wsb.fitnesstracker.training.internal;

/**
 * Enum representing different types of activities.
 */
public enum ActivityType {

    /**
     * Running activity type.
     */
    RUNNING("Running"),

    /**
     * Cycling activity type.
     */
    CYCLING("Cycling"),

    /**
     * Walking activity type.
     */
    WALKING("Walking"),

    /**
     * Swimming activity type.
     */
    SWIMMING("Swimming"),

    /**
     * Tennis activity type.
     */
    TENNIS("Tennis");

    private final String displayName;

    /**
     * Constructor for ActivityType enum.
     *
     * @param displayName The display name of the activity type
     */
    ActivityType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Gets the display name of the activity type.
     *
     * @return The display name of the activity type
     */
    public String getDisplayName() {
        return displayName;
    }

}