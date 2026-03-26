package org.application.calculator;

import org.application.calculator.controller.UserController;
import org.application.calculator.dao.ConnectionToDB;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {
    ConnectionToDB connectionToDB;

    public Main(ConnectionToDB connectionToDB) {
        this.connectionToDB = connectionToDB;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args)  {
        UserController  userController = new UserController();
        userController.initial();

    }
}
