package org.application.calculator.dao;

import org.application.calculator.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;


@Service
public class ConnectionToDB {
    private static final String MESSAGE_FOR_USER = "User with this username already exists";
    private static final String MESSAGE_PROBLEMS_WITH_LOG_IN = "This username or password is incorrect";
    private static final String MESSAGE_FOR_USER_DELETE_VERSION = "User with this ID is already deleted";
    @Autowired
    private final UserDAO userDao;


    public ConnectionToDB(UserDAO userDao) {
        this.userDao = userDao;
    }

    public void createUser(String firstName, String lastName,
                           String username, String password) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(password);

        if (checkExistUser(username)) {
            userDao.save(user);
        } else {
            System.out.println(MESSAGE_FOR_USER);
        }

    }

    public void deleteUser(int userId) {
        if (userDao.findById(userId).isPresent()) {
            userDao.deleteById(userId);
        } else {
            System.out.println(MESSAGE_FOR_USER_DELETE_VERSION);
        }
    }

    private User checkUserForLogin(String username, String password) {
        for (User user : userDao.findAll()) {
            if (user.getUsername().equals(username) &&
                    user.getPassword().equals(password)) {
                return user;
            }

        }
        return null;

    }

    private boolean checkExistUser(String username) {

        for (User user : userDao.findAll()) {
            if (user.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }


}
