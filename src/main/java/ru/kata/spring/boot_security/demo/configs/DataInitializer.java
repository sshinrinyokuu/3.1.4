package ru.kata.spring.boot_security.demo.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.dto.UserResponseDto;
import ru.kata.spring.boot_security.demo.dto.UserUpdateDto;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserService userService;


    public DataInitializer(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
            roleRepository.save(new Role("ROLE_ADMIN"));
        }
        if (roleRepository.findByName("ROLE_USER").isEmpty()) {
            roleRepository.save(new Role("ROLE_USER"));
        }

        if (userService.showUsers().isEmpty()) {
            UserUpdateDto admin = new UserUpdateDto();
            admin.setUsername("admin");
            admin.setLastName("admin");
            admin.setPassword("111");
            admin.setEmail("admin@gmail.com");
            admin.setAge(22);
            admin.setRoleName(List.of("ROLE_ADMIN"));
            userService.saveUser(admin);

            UserUpdateDto userDto = new UserUpdateDto();
            userDto.setUsername("user");
            userDto.setLastName("user");
            userDto.setPassword("222");
            userDto.setEmail("user@gmail.com");
            userDto.setAge(30);
            userDto.setRoleName(List.of("ROLE_USER"));
            userService.saveUser(userDto);
        }



    }

}