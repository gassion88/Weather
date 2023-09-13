package com.gassion.weather.service;

import com.gassion.weather.dto.UserRegisterRequestDTO;
import com.gassion.weather.entity.User;
import org.springframework.validation.BindingResult;

import java.util.Optional;

public interface RegisterService {

    void saveUserOrSetResult(UserRegisterRequestDTO userRegisterRequestDTO, BindingResult result);

    Optional<User> findUserByEmail(String email);

}
