package com.edu.postgrad.game.teams.rest;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @ApiOperation("Get home page of the website")
    @GetMapping("/")
    public String displayHomePage(Model model) {
        return "home";
    }
}
