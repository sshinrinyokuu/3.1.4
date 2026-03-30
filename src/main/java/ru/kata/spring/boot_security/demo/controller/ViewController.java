package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.User;

@Controller
public class ViewController {

    @GetMapping("/admin")
    public String showAdminPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "admin";
    }

    @GetMapping("/user")
    public String showUserPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
