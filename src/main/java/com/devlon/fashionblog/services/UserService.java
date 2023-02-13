package com.devlon.fashionblog.services;

import com.devlon.fashionblog.dto.LoginDto;
import com.devlon.fashionblog.dto.UserDto;
import com.devlon.fashionblog.entity.User;

public interface UserService {
    UserDto registerUser(UserDto userDto);

    UserDto loginUser(LoginDto loginDto);

    User getUserById(Long userId);
}
