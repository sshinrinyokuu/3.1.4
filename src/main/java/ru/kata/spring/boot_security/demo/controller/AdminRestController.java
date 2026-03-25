package ru.kata.spring.boot_security.demo.controller;

import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dto.UserResponseDto;
import ru.kata.spring.boot_security.demo.dto.UserUpdateDto;
import ru.kata.spring.boot_security.demo.model.User;
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
    public List<User> getAllUsers() {
        return userService.showUsers();
    }

    @PostMapping("/add")
    public UserUpdateDto addUser(@RequestBody UserUpdateDto dto) {
        User savedUser = userService.saveUser(
                dto.getUsername(), dto.getPassword(), dto.getLastName(),
                dto.getAge(), dto.getEmail(), dto.getRoleName()
        );

        UserUpdateDto addDtoUser = new UserUpdateDto();
        addDtoUser.setUsername(savedUser.getUsername());
        addDtoUser.setLastName(savedUser.getLastName());
        addDtoUser.setPassword(savedUser.getPassword());
        addDtoUser.setAge(savedUser.getAge());
        addDtoUser.setEmail(savedUser.getEmail());
        addDtoUser.setRoleName(savedUser.getRolesString());
        return addDtoUser;
    }

    @PutMapping("/edit")
    public UserResponseDto editUser(@RequestBody UserResponseDto dto) {
       User userToEdit = userService.editUser(
               dto.getId(),
               dto.getUsername(),
               dto.getPassword(),
               dto.getLastName(),
               dto.getAge(),
               dto.getEmail(),
               dto.getRoleName()
       );
       UserResponseDto editDtoUser = new UserResponseDto();
        editDtoUser.setId(userToEdit.getId());
        editDtoUser.setUsername(userToEdit.getUsername());
        editDtoUser.setLastName(userToEdit.getLastName());
        editDtoUser.setEmail(userToEdit.getEmail());
        editDtoUser.setAge(userToEdit.getAge());
        editDtoUser.setRoleName(userToEdit.getRolesString());
        return editDtoUser;

    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestBody UserResponseDto dto) {
        userService.deleteUser(dto.getId());
    }
}