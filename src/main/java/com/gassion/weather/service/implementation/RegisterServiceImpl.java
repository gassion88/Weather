package com.gassion.weather.service.implementation;

import com.gassion.weather.dto.UserRegisterRequestDTO;
import com.gassion.weather.entity.Role;
import com.gassion.weather.entity.User;
import com.gassion.weather.repository.RoleRepository;
import com.gassion.weather.repository.UserRepository;
import com.gassion.weather.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RegisterServiceImpl implements RegisterService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserRegisterRequestDTO userRegisterRequestDTO) {
        User user = new User();
        user.setName(userRegisterRequestDTO.getFirstName() + " " + userRegisterRequestDTO.getLastName());
        user.setEmail(userRegisterRequestDTO.getEmail());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userRegisterRequestDTO.getPassword()));

        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}
