package com.elena.companies_crud.api.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("version", "1.0.0");
        model.addAttribute("description", "Companies CRUD Service – Clean Architecture");
        model.addAttribute("timestamp", LocalDateTime.now().toString());

        return "index"; // templates/index.html
    }

}