package com.gassion.weather.service.implementation;

import com.gassion.weather.dto.UserRegisterRequestDTO;
import com.gassion.weather.entity.Role;
import com.gassion.weather.entity.User;
import com.gassion.weather.repository.RoleRepository;
import com.gassion.weather.repository.UserRepository;
import com.gassion.weather.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUserOrSetResult(UserRegisterRequestDTO userRegisterRequestDTO, BindingResult result) {
        User existingUser = findUserByEmail(userRegisterRequestDTO.getEmail());
        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
            return;
        }

        User user = new User();
        user.setName(userRegisterRequestDTO.getFirstName() + " " + userRegisterRequestDTO.getLastName());
        user.setEmail(userRegisterRequestDTO.getEmail());
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
        return userRepository.findByEmail(email).get();
    }

    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}
