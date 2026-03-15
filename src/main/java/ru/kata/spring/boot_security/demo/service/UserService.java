package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;


public interface UserService {
    User findById(Long id);

    void saveUser(String username, String password, String lastName, Integer age, String email, String roleName);

    void deleteUser(Long id);

    void editUser(Long id,String username, String password, String lastName, Integer age, String email, String roleName);

    List<User> showUsers();
}
