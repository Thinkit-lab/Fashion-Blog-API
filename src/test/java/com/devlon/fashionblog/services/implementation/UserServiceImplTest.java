package com.devlon.fashionblog.services.implementation;

import com.devlon.fashionblog.dto.LoginDto;
import com.devlon.fashionblog.dto.UserDto;
import com.devlon.fashionblog.entity.User;
import com.devlon.fashionblog.exception.NotFoundException;
import com.devlon.fashionblog.repository.UserRepository;
import com.devlon.fashionblog.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;
    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .email("olu@gmail.com")
                .firstName("Olu")
                .lastName("Ade")
                .password("12345").build();
        List<User> userList = new ArrayList<>();
        userList.add(user);

        Mockito.when(userRepository.findAll()).thenReturn(userList);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword()))
                .thenReturn(user);
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
    }

    @Test
    @Disabled
    void registerUser() {
        UserDto userDTO = UserDto.builder()
                .email("olu@gmail.com")
                .firstName("Olu")
                .lastName("Ade")
                .password("12345").build();
        UserDto user = userService.registerUser(userDTO);
        assertNull(user);
    }

    @Test
    void loginUser() {
        LoginDto userDTO = LoginDto.builder()
                .email("olu@gmail.com")
                .password("12345").build();

        UserDto userDto = userService.loginUser(userDTO);
        assertNotNull(userDto);
    }

    @Test()
    void loginUserIf_userIsNull_shouldThrowException() {
        LoginDto userDTO = LoginDto.builder()
                .email("ol@gmail.com")
                .password("12345").build();

        Throwable exception = assertThrows(NotFoundException.class, () -> {
            userService.loginUser(userDTO);
        });

        assertEquals(exception.getMessage(), "Email or Password not found");
    }

    @Test
    void getUserById() {
        UserDto user = UserDto.builder()
                .user_Id(1L)
                .email("olu@gmail.com")
                .firstName("Olu")
                .lastName("Ade")
                .password("12345").build();
        User userDto = userService.getUserById(1L);
        assertEquals(user.getFirstName(), userDto.getFirstName());
    }

    @Test
    void getUserById_ifUserIsEmpty_shouldThrowException() {
//        UserDto user = UserDto.builder()
//                .user_Id(1L)
//                .email("olu@gmail.com")
//                .firstName("Olu")
//                .lastName("Ade")
//                .password("12345").build();

        Throwable exception = assertThrows(NotFoundException.class, ()-> {
            userService.getUserById(10L);
        });
        assertEquals(exception.getMessage(), "User not found");
    }
}