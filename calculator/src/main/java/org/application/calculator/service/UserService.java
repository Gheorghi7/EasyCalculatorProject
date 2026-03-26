package org.application.calculator.service;

import org.application.calculator.dao.ConnectionToDB;
import org.application.calculator.models.User;

public class UserService {
    private final ConnectionToDB connectionToDB;


    public UserService(ConnectionToDB connectionToDB) {
        this.connectionToDB = connectionToDB;
    }

    public Integer calculatorService(int firstValue, int secondValue, String operationType) {
        int result = 0;
        switch (operationType) {
            case "+" -> result = firstValue + secondValue;
            case "-" -> result = firstValue - secondValue;
            case "*" -> result = firstValue * secondValue;
            case "/" -> result = firstValue / secondValue;
            default -> throw new IllegalStateException("Unexpected value: " + operationType);

        }
        return result;

    }

    public void createUser(User user) {
        connectionToDB.createUser(user);
    }

    public void deleteUser(User user) {
        connectionToDB.deleteUser(user.getId());
    }

}
