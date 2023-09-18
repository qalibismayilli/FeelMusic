package com.company.feelmusic.service;

import com.company.feelmusic.dto.request.UserRequestDto;
import com.company.feelmusic.dto.response.UserResponseDto;
import com.company.feelmusic.model.Role;
import com.company.feelmusic.model.User;
import com.company.feelmusic.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private UserResponseDto convertTo(User user) {
        return new UserResponseDto(user.getUsername(), user.getEmail(), user.getRole());
    }

    public UserResponseDto createUser(UserRequestDto request) {
        User user = new User.Builder().username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword())).email(request.getEmail())
                .role(Role.USER).feelContainers(new ArrayList<>()).build();

        User fromDB = userRepository.save(user);

        return convertTo(fromDB);
    }

    public UserResponseDto createAdmin(UserRequestDto request) {
        User user = new User.Builder().username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword())).email(request.getEmail())
                .role(Role.ADMIN).feelContainers(new ArrayList<>()).build();

        User fromDB = userRepository.save(user);

        return convertTo(fromDB);
    }

    public UserResponseDto findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return convertTo(user);
    }

    public List<UserResponseDto> listByEmail(String email) {
        List<User> users = userRepository.findAllByEmail(email);
        return users.stream().map(user -> convertTo(user)).toList();
    }


}
