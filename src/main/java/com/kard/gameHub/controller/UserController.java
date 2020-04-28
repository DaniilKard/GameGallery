package com.kard.controller;

import com.kard.entity.User;
import com.kard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public String postUser(@RequestBody User user, Model model) {
        User savedUser = userService.saveUser(user);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/user/{id}")
    public String getUser(
            @PathVariable("id") String id,
            Model model) throws Exception {
        User user = userService.getUser(Integer.parseInt(id));
        model.addAttribute("user", user);
        return "user";
    }

    @PutMapping("/user/{id}")
    public String putUser(@RequestBody User user, @PathVariable("id") long id, Model model) {
        User currentUser = null;
        try {
            currentUser = userService.getUser(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (currentUser != null) {
            if (user.getAge() != 0) {
                currentUser.setAge(user.getAge());
            }
            if (user.getName() != null) {
                currentUser.setName(user.getName());
            }
            if (user.getSurname() != null) {
                currentUser.setSurname(user.getSurname());
            }
        }
        User savedUser = userService.saveUser(currentUser);
        model.addAttribute("user", currentUser);
        return "user";
    }

    @DeleteMapping("/user/{id}")
    public String getUser(@PathVariable("id") long id, Model model) throws Exception {
        userService.deleteUser(id);
        return "user";
    }
}