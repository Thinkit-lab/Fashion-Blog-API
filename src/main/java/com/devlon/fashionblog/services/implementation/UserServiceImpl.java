package com.devlon.fashionblog.services.implementation;

import com.devlon.fashionblog.dto.LoginDto;
import com.devlon.fashionblog.dto.UserDto;
import com.devlon.fashionblog.entity.User;
import com.devlon.fashionblog.exception.AlreadyExistException;
import com.devlon.fashionblog.exception.NotFoundException;
import com.devlon.fashionblog.repository.UserRepository;
import com.devlon.fashionblog.services.UserService;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);

        User existingUser = userRepository.findByEmail(userDto.getEmail());
        if (existingUser != null) {
            throw new AlreadyExistException("A user with the email already exists! Try another email");
        }

        return mapToUserDto(userRepository.save(user));
    }

    @Override
    public UserDto loginUser(LoginDto loginDto) {
        User user = userRepository.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
        if (user == null) {
            throw new NotFoundException("Email or Password not found");
        }
        return mapToUserDto(user);
    }

    @Override
    public User getUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        return user.get();
    }

    public UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .isVerified(user.getIsVerified())
                .user_Id(user.getUser_Id())
                .build();
    }
}
