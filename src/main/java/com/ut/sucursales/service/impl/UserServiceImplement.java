package com.ut.sucursales.service.impl;

import com.ut.sucursales.exceptions.exceptions_class.ConflictException;
import com.ut.sucursales.mappers.UserMapper;
import com.ut.sucursales.model.dto.TokenResponseDto;
import com.ut.sucursales.model.dto.UserDto;
import com.ut.sucursales.model.entity.User;
import com.ut.sucursales.repository.UserRepository;
import com.ut.sucursales.service.interfaces.UserService;
import com.ut.sucursales.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Override
    public UserDto register(UserDto userDto) {
        if (repository.existsByUsername(userDto.username())) throw new ConflictException("El nombre de usuario ya existe");
        User user = mapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
        return mapper.toDto(user);
    }

    @Override
    public TokenResponseDto login(UserDto userDto) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate
                    (new UsernamePasswordAuthenticationToken(userDto.username(),userDto.password()));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Credenciales inválidas");
        }
        org.springframework.security.core.userdetails.User user =
                (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        String token = jwtUtils.generateToken(
                user.getUsername(), Map.of());
        return new TokenResponseDto(token, "bearer");
    }
}
