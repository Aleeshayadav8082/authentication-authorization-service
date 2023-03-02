package com.maveric.authenticationauthorizationservice.service;

import com.maveric.authenticationauthorizationservice.constant.Gender;
import com.maveric.authenticationauthorizationservice.dto.UserDto;
import com.maveric.authenticationauthorizationservice.exception.UserNotFoundException;
import com.maveric.authenticationauthorizationservice.feignclient.FeignConsumer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Date;


import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    FeignConsumer feignConsumer;

    @MockBean
    private JWTService jwtService;

    @InjectMocks
    UserService userService;

    @Mock
    UserDetails userDetails;

    @Test
    void loadUserByUsername() {
        when(feignConsumer.getUserByEmail(anyString())).thenReturn(getSampleUserDto());

        UserDetails userDetails1 = userService.loadUserByUsername("aleeshay@maveric-systems.com");

        UserDto userDto = feignConsumer.getUserByEmail("aleeshay@maveric-systems.com").getBody();

        assertNotNull(userDto);
        assertSame("aleeshay@maveric-systems.com", userDto.getEmail());
    }

    public ResponseEntity<UserDto> getSampleUserDto(){
        UserDto user = new UserDto();
        user.setFirstName("Aleesha");
        user.setLastName("Yadav");
        user.setMiddleName("");
        user.setEmail("aleeshay@maveric-systems.com");
        user.setPassword("Pass@word11");
        user.setGender(Gender.FEMALE);
        user.setDateOfBirth(Date.from(Instant.parse("1994-10-22T00:00:00Z")));
        user.setAddress("Pune");
        user.setPhoneNumber("1234567890");

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}