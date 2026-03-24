package org.application.calculator.dao;

import org.application.calculator.dto.UserCreataeDTO;
import org.application.calculator.dto.UserDeleteDTO;
import org.application.calculator.dto.UserLogIn;
import org.application.calculator.models.User;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {


}
