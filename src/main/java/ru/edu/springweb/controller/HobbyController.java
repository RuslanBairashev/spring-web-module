package ru.edu.springweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HobbyController {

    @GetMapping("/hobby")
    public String getHome(Model model) {
        return "hobby";
    }
}