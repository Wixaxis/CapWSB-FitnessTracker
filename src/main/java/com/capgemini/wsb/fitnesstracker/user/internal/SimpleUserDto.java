package com.capgemini.wsb.fitnesstracker.user.internal;

/**
 * Data Transfer Object (DTO) for simple user information.
 * This record holds the user's unique identifier, first name, and last name.
 *
 * @param id The unique identifier of the user
 * @param firstName The first name of the user
 * @param lastName The last name of the user
 */
record SimpleUserDto(Long id, String firstName, String lastName) {
}