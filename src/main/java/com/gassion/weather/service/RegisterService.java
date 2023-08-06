package com.gassion.weather.service;

import com.gassion.weather.dto.UserRegisterRequestDTO;
import com.gassion.weather.entity.User;
import org.springframework.validation.BindingResult;

public interface RegisterService {
    void saveUserOrSetResult(UserRegisterRequestDTO userRegisterRequestDTO, BindingResult result);

    User findUserByEmail(String email);
}
