package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dto.UserResponseDto;
import ru.kata.spring.boot_security.demo.dto.UserUpdateDto;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


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
    public List<UserResponseDto> showUsers() {
        return userRepository.findAll().stream()
                .map(UserResponseDto::new)
                .toList();
    }

    @Override
    public UserUpdateDto saveUser(UserUpdateDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setLastName(dto.getLastName());
        user.setAge(dto.getAge());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        Set<Role> roles = dto.getRoleName().stream()
                .map(roleService::findByName)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        if (dto.getRoleName().contains("ROLE_ADMIN")) {
            roles.add(roleService.findByName("ROLE_USER"));
        }

        user.setRoles(roles);

        User savedUser = userRepository.save(user);
        return new UserUpdateDto(savedUser);
    }

    @Override
    public UserResponseDto editUser(UserResponseDto dto) {
        User user = userRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(dto.getUsername());
        user.setLastName(dto.getLastName());
        user.setAge(dto.getAge());
        user.setEmail(dto.getEmail());

        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        Set<Role> roles = dto.getRoleName().stream()
                .map(roleService::findByName)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        if (dto.getRoleName().contains("ROLE_ADMIN")) {
            roles.add(roleService.findByName("ROLE_USER"));
        }

        user.setRoles(roles);

        User updatedUser = userRepository.save(user);
        return new UserResponseDto(updatedUser);
    }

    @Override
    public void deleteUser(UserResponseDto dto) {
        userRepository.deleteById(dto.getId());
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}