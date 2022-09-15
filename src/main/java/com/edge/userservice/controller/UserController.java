package com.edge.userservice.controller;

import com.edge.userservice.dto.NewPasswordDTO;
import com.edge.userservice.exception.ErrorDTO;
import com.edge.userservice.exception.PasswordIncorrectException;
import com.edge.userservice.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserDetailsServiceImpl service;

    @GetMapping("/")
    public String index() {
        return "home";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/change-password")
    public String changePassword() {
        return "change-password";
    }

    @PostMapping("/update-password")
    public String updatePassword(NewPasswordDTO dto, Model model) throws Exception {
        try {
            service.changePassword(dto);
        } catch (PasswordIncorrectException e) {
            ErrorDTO errorDTO = new ErrorDTO(e.getMessage());
            model.addAttribute("errorDTO", errorDTO);
            return "/error";
        }
        return "home";
    }

}
