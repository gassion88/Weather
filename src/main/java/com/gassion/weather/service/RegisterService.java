package com.gassion.weather.service;

import com.gassion.weather.dto.UserDTO;
import com.gassion.weather.entity.User;

public interface RegisterService {
    void saveUser(UserDTO userDTO);

    User findUserByEmail(String email);
}
