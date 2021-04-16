package com.codegym.demo.controller;

import com.codegym.demo.model.User;
import com.codegym.demo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public ModelAndView showLoginForm() {
        ModelAndView modelAndView = new ModelAndView("/login");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute User user) {
        User user1 = userService.findByUsername(user.getUsername());
        if (user.getUsername().equals(user1.getUsername())) {
            if (passwordEncoder.matches(user.getPassword(), user1.getPassword())) {
                return new ModelAndView("/product/list");
            }
        }
        return new ModelAndView("/login");
    }

    @GetMapping("/admin")
    public ModelAndView demoLoginWithAdmin(){
        return new ModelAndView("/product/list");
    }
}
