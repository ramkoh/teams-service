package com.edu.postgrad.game.teams.rest;

import com.edu.postgrad.game.teams.dto.Player;
import com.edu.postgrad.game.teams.dto.Position;
import com.edu.postgrad.game.teams.service.PlayerService;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @ApiOperation("Render page to add player")
    @GetMapping("/player")
    public String showFormOfCreatePlayer(final Model model) {
        model.addAttribute("player", new Player());
        model.addAttribute("positions", Position.values());
        model.addAttribute("message", Strings.EMPTY);
        return "players/add-player";
    }

    @ApiOperation("Save player")
    @PostMapping("/player")
    public String createPlayer(@Valid Player player, Errors errors, Model model, HttpServletResponse httpResponse) {
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

    @ApiOperation("View all players")
    @GetMapping("/players")
    public String getAllPlayers(final Model model) {
        Iterable<Player> players = playerService.getAllPlayers();
        model.addAttribute("players", players);
        model.addAttribute("source", "players");
        return "players/view-players";
   }

    //To mock service in unit tests
    public void setPlayerService(PlayerService playerService){
        this.playerService = playerService;
    }

    @PostMapping("/test/player")
    public String createPlayerTest(@RequestBody @Valid Player player, Errors errors, Model model, HttpServletResponse httpResponse) {
        return createPlayer(player, errors, model, httpResponse);
    }
}
