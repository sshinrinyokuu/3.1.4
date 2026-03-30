package ru.kata.spring.boot_security.demo.controller;


import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dto.UserResponseDto;
import ru.kata.spring.boot_security.demo.dto.UserUpdateDto;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;


@RestController
@RequestMapping("api/admin")
public class AdminRestController {

    private final UserService userService;

    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.showUsers();
    }

    @PostMapping("/add")
    public UserUpdateDto addUser(@RequestBody UserUpdateDto userDto) {
        return userService.saveUser(userDto);
    }

    @PutMapping("/edit")
    public UserResponseDto editUser(@RequestBody UserResponseDto dto) {
        return userService.editUser(dto);

    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestBody UserResponseDto dto) {
        userService.deleteUser(dto);
    }

}