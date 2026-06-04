package com.ut.sucursales.service.interfaces;

import com.ut.sucursales.model.dto.TokenResponseDto;
import com.ut.sucursales.model.dto.UserDto;

public interface UserService {
    UserDto register(UserDto userDto);

    TokenResponseDto login(UserDto userDto);
}
