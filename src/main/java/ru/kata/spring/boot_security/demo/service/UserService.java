package ru.kata.spring.boot_security.demo.service;


import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dto.UserResponseDto;
import ru.kata.spring.boot_security.demo.dto.UserUpdateDto;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;


public interface UserService {
    List<UserResponseDto> showUsers();
    UserUpdateDto saveUser(UserUpdateDto dto);
    UserResponseDto editUser(UserResponseDto dto);
    void deleteUser(UserResponseDto dto);
    User findById(Long id);

}
