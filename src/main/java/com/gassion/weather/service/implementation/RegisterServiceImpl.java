package com.gassion.weather.service.implementation;

import com.gassion.weather.dto.UserRegisterRequestDTO;
import com.gassion.weather.entity.Role;
import com.gassion.weather.entity.User;
import com.gassion.weather.repository.RoleRepository;
import com.gassion.weather.repository.UserRepository;
import com.gassion.weather.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUserOrSetResult(UserRegisterRequestDTO userRegisterRequestDTO, BindingResult result) {
        if(isEmailAlreadyExists(userRegisterRequestDTO.getEmail())){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
            return;
        }

        User user = buildNewUser(userRegisterRequestDTO);

        userRepository.save(user);
    }

    private User buildNewUser(UserRegisterRequestDTO userRegisterRequestDTO) {
        User user = new User();
        user.setName(userRegisterRequestDTO.getFirstName() + " " + userRegisterRequestDTO.getLastName());
        user.setEmail(userRegisterRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterRequestDTO.getPassword()));

        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        return user;
    }

    private boolean isEmailAlreadyExists(String email) {
        return findUserByEmail(email).isPresent();
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}
