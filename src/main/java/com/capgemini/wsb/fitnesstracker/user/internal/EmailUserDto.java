package com.capgemini.wsb.fitnesstracker.user.internal;

import jakarta.annotation.Nullable;

/**
 * Data Transfer Object (DTO) for user email information.
 * This record holds the user's unique identifier and email address.
 *
 * @param Id The unique identifier of the user, can be null
 * @param email The email address of the user
 */
record EmailUserDto(@Nullable Long Id, String email) { }