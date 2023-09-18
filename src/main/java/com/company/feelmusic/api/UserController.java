package com.company.feelmusic.api;

import com.company.feelmusic.dto.request.UserRequestDto;
import com.company.feelmusic.dto.response.UserResponseDto;
import com.company.feelmusic.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/createUser")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto request){
        return ResponseEntity.ok(userService.createUser(request));
    }

    @PostMapping("/createAdmin")
    public ResponseEntity<UserResponseDto> createAdmin(@RequestBody UserRequestDto request){
        return ResponseEntity.ok(userService.createAdmin(request));
    }

    @GetMapping("/findByUsername")
    public ResponseEntity<UserResponseDto> findByUsername(@RequestParam String username){
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @GetMapping("/listByEmail")
    public ResponseEntity<List<UserResponseDto>> listByEmail(@RequestParam String email){
        return ResponseEntity.ok(userService.listByEmail(email));
    }


}
