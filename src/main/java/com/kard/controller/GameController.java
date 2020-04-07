package com.kard.controller;

import com.kard.service.UserService;
import com.kard.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class GameController {
    @Autowired
    private UserService userService;

    @GetMapping("/game/{name}/{surname}/{age}")
    public String getUser(
            @PathVariable("name") String name,
            @PathVariable("surname") String surname,
            @PathVariable("age") String age,
            Model model) {
        User user = new User(name, surname, Integer.parseInt(age));
        User savedUser = userService.saveUser(user);
        model.addAttribute("name", name);
        model.addAttribute("surname", surname);
        model.addAttribute("age", age);
        return "game";
    }

    @GetMapping("/game/{id}")
    public String getGameById(@PathVariable("id") String id, Model model) throws Exception {
        User user = userService.getUser(Integer.parseInt(id));
        model.addAttribute("id", id);
        return "game";
    }
}