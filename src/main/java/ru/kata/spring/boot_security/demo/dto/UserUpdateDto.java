package ru.kata.spring.boot_security.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {

    private String username;
    private String lastName;
    private String email;
    private Integer age;
    private List<String> roleName;
    private String password;

    public UserUpdateDto(User user) {
        this.username = user.getUsername();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.age = user.getAge();
        this.roleName = user.getRoles().stream()
                .map(Role::getName)
                .toList();
        this.password = user.getPassword();
    }

}
