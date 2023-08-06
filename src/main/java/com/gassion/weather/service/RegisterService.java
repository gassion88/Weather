package com.gassion.weather.service;

import com.gassion.weather.dto.UserRegisterRequestDTO;
import com.gassion.weather.entity.User;

public interface RegisterService {
    void saveUser(UserRegisterRequestDTO userRegisterRequestDTO);

    User findUserByEmail(String email);
}
