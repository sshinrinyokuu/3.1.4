package ru.kata.spring.boot_security.demo.service;


import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dto.UserResponseDto;
import ru.kata.spring.boot_security.demo.dto.UserUpdateDto;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;


public interface UserService {
    User findById(Long id);
    User saveUser(String username, String password, String lastName, Integer age, String email, String roleName);
    void deleteUser(Long id);
    User editUser(Long id, String username, String password, String lastName, Integer age, String email, String roleName);
    List<User> showUsers();



    User saveUser(UserUpdateDto dto);
    User editUser(UserResponseDto dto);
}
