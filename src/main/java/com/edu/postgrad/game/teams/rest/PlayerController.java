package com.edu.postgrad.game.teams.rest;

import com.edu.postgrad.game.teams.dto.Player;
import com.edu.postgrad.game.teams.dto.Position;
import com.edu.postgrad.game.teams.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;

@Controller
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @GetMapping("/player")
    public String addPlayerStartUp(final Model model) {
        model.addAttribute("player", new Player());
        model.addAttribute("positions", Position.values());
        model.addAttribute("message", null);
        return "players/add-player";
    }


    @PostMapping("/player")
    public String addPlayer(@Valid Player player, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("positions", Position.values());
            return "players/add-player";
        }

        playerService.savePlayer(player);
        model.addAttribute("player", player);
        model.addAttribute("message", String.format("Player %s %s created successfully", player.getFirstName(),
                player.getLastName()));
        return "players/add-player";
    }

}
