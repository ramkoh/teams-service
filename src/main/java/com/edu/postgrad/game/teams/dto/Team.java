package com.edu.postgrad.game.teams.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel(description = "Entity to hold Team data")
@Entity
@Table(name = "teams")
@Component
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty("Name of the team")
    @NotNull
    private String name;

    @ApiModelProperty("Short code of team")
    @NotNull
    @Size(max = 3, min=3,  message = "More than 3 characters are not allowed.")
    private String code;

    @ApiModelProperty(notes = "Players playing for the team")
    @Size(max = 11, message = "Team cannot have more than 11 players")
    @OneToMany(targetEntity = Player.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Player> players;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayers(List<Player> players) {
        getPlayers().addAll(players);
    }

    public void addPlayer(Player player) {
        getPlayers().add(player);
    }

    public Team(){}

    public Team(String name, String code, List<Player> players) {
        this.name = name;
        this.code = code;
        this.players = players;
    }

}
