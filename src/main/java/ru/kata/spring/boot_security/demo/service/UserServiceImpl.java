package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void saveUser(String username, String password, String lastName, Integer age, String email, String roleName) {
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(username, encodedPassword, lastName, age, email);
        Role role = roleService.findByName(roleName);
        Set<Role> roles = new LinkedHashSet<>();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void editUser(Long id, String username, String password, String lastName, Integer age, String email, String roleName) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(username);
        user.setLastName(lastName);
        user.setAge(age);
        user.setEmail(email);

        if (password != null && !password.isEmpty()) {
            user.setPassword(passwordEncoder.encode(password));
        }
        Role role = roleService.findByName(roleName);
        Set<Role> roles = new LinkedHashSet<>();
        if(roleName.equals("ROLE_ADMIN")) {
            roles.add(roleService.findByName("ROLE_USER"));
        }
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
    }


    @Override
    public List<User> showUsers() {
        return userRepository.findAll();
    }
}