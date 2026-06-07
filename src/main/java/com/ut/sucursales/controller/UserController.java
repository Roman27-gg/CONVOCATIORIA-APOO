package com.ut.sucursales.controller;

import com.ut.sucursales.model.dto.TokenResponseDto;
import com.ut.sucursales.model.dto.UserDto;
import com.ut.sucursales.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto register(@RequestBody UserDto userDto) {
        return userService.register(userDto);
    }

    @PostMapping("/login")
    public TokenResponseDto login(@RequestBody UserDto userDto) {
        return userService.login(userDto);
    }
}