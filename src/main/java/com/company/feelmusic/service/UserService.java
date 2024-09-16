package com.company.feelmusic.service;

import com.company.feelmusic.dto.request.UserRequestDto;
import com.company.feelmusic.dto.response.UserResponseDto;
import com.company.feelmusic.exception.GenericException;
import com.company.feelmusic.model.Role;
import com.company.feelmusic.model.User;
import com.company.feelmusic.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    protected UserResponseDto convertToResponse(User user) {
        return new UserResponseDto(user.getUsername(), user.getEmail(), user.getRole());
    }

    private UserResponseDto create(UserRequestDto request, Role role) {
        User user = new User.Builder().username(Objects.requireNonNull(request.getUsername()))
                .password(passwordEncoder.encode(request.getPassword()))
                .email(Objects.requireNonNull(request.getEmail()))
                .role(role).feelContainers(new ArrayList<>()).build();

        User fromDB = userRepository.save(user);

        return convertToResponse(fromDB);
    }

    @Transactional
    public UserResponseDto createUser(UserRequestDto request) {
        return create(request, Role.USER);
    }

    @Transactional
    public UserResponseDto createAdmin(UserRequestDto request) {
        return create(request, Role.ADMIN);
    }

    protected User findOriginalUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public UserResponseDto findByUsername(String username) {
        User user = Optional.of(userRepository.findByUsername(username))
                .orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND,"User not found by give Username"));
        return convertToResponse(user);
    }

    public List<UserResponseDto> listByEmail(String email) {
        List<User> users = Optional.of(userRepository.findAllByEmail(email))
                .orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND, "User not found by given Email"));
        return users.stream().map(user -> convertToResponse(user)).toList();
    }


}
