package org.application.calculator;

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
        connectionToDB.createUser("Richard", "Perelman",
                "password1222", "userRichard");
        System.out.println("User created successfully");
    }
}
