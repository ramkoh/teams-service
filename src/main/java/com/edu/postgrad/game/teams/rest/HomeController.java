package com.edu.postgrad.game.teams.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String displayHomePage(Model model) {
        return "home";
    }
}
