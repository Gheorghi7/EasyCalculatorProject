package org.application.calculator.dto;

public record UserCreateDTO(String firstName, String lastName,
                            String username, String password) {}
