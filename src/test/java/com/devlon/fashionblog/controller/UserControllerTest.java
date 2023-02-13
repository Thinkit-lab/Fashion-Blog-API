package com.devlon.fashionblog.controller;

import com.devlon.fashionblog.dto.LoginDto;
import com.devlon.fashionblog.dto.UserDto;
import com.devlon.fashionblog.entity.User;
import com.devlon.fashionblog.services.implementation.UserServiceImpl;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;
    private UserDto user;

    @BeforeEach
    void setUp() {
        user = UserDto.builder()
                .email("olu@gmail.com")
                .firstName("Olu")
                .lastName("Ade")
                .password("12345").build();
        List<UserDto>userList = new ArrayList<>();
        userList.add(user);

        Mockito.when(userService.registerUser(user)).thenReturn(UserDto.builder()
                .email("olu@gmail.com")
                .firstName("Olu")
                .lastName("Ade")
                .password("12345").build());
    }

    @Test
    void registerUser_thenReturnUser() throws Exception {
        UserDto inputUser = UserDto.builder()
                .email("olu@gmail.com")
                .firstName("Olu")
                .lastName("Ade")
                .password("12345").build();

        Mockito.when(userService.registerUser(inputUser)).thenReturn(UserDto.builder()
                .email("olu@gmail.com")
                .firstName("Olu")
                .lastName("Ade")
                .password("12345").build());
        System.out.println(userService.registerUser(inputUser));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/fashion-blog/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"email\":\"olu@gmail.com\",\n" +
                        "\t\"firstName\":\"Olu\",\n" +
                        "\t\"password\":\"12345\",\n" +
                        "\t\"lastName\":\"Ade\"\n" +
                        "}")).andExpect(status().isCreated());
    }

    @Test
    void loginUser_thenReturnUser() throws Exception {
        LoginDto inputUser = LoginDto.builder()
                .email("olu@gmail.com")
                .password("12345").build();
        Mockito.when(userService.loginUser(inputUser))
                .thenReturn(UserDto.builder()
                        .email("olu@gmail.com")
                        .firstName("Olu")
                        .lastName("Ade")
                        .password("12345").build());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/fashion-blog/user")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(status().isFound())
                .andReturn();
    }
}