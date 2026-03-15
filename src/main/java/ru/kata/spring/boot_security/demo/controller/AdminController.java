package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showAdminPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("listOfUsers", userService.showUsers());
        return "index";
    }

    @PostMapping("/add")
    public String addUser(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam("lastName") String lastName,
                          @RequestParam("age") Integer age,
                          @RequestParam("email") String email,
                          @RequestParam("role") String role) {
        userService.saveUser(username, password, lastName, age, email, role);
        return "redirect:/admin";
    }

    @PostMapping("/edit")
    public String editUser(@RequestParam("id") Long id,
                           @RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("roles") String roles,
                           @RequestParam("lastName") String lastName,
                           @RequestParam("age") Integer age,
                           @RequestParam("email") String email) {
        userService.editUser(id, username, password, lastName, age, email, roles);
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}