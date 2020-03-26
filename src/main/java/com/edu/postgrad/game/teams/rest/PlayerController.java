package com.edu.postgrad.game.teams.rest;

import com.edu.postgrad.game.teams.dto.Player;
import com.edu.postgrad.game.teams.dto.Position;
import com.edu.postgrad.game.teams.service.PlayerService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class PlayerController {

    @Autowired
    PlayerService playerService;

   /* @Autowired
    public PlayerController(PlayerService playerService){
       this.playerService = playerService;
    }*/

    @GetMapping("/player")
    public String showFormOfCreatePlayer(final Model model) {
        model.addAttribute("player", new Player());
        model.addAttribute("positions", Position.values());
        model.addAttribute("message", Strings.EMPTY);
        return "players/add-player";
    }


   @PostMapping("/player")
    public String createPlayer(@RequestBody @Valid Player player, Errors errors, Model model, HttpServletResponse httpResponse) {

        if (errors.hasErrors()) {
            model.addAttribute("positions", Position.values());
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "players/add-player";
        }

        playerService.savePlayer(player);
        model.addAttribute("player", player);
        model.addAttribute("message", String.format("Player %s %s created successfully", player.getFirstName(),
                player.getLastName()));
        httpResponse.setStatus(HttpServletResponse.SC_CREATED);
        return "players/add-player";
    }

  /*  @PostMapping("/player")
    public String createPlayer(@RequestBody Player player, Errors errors) {
        playerService.savePlayer(player);
        return "players/add-player";
    }*/

    public void setPlayerService(PlayerService playerService){
        this.playerService = playerService;
    }
}
