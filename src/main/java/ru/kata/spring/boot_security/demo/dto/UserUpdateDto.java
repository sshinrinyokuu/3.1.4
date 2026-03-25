package ru.kata.spring.boot_security.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {

    private String username;
    private String lastName;
    private String email;
    private Integer age;
    private String roleName;
    private String password;

}
