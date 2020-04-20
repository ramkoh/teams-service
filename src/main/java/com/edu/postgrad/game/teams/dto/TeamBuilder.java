package com.edu.postgrad.game.teams.dto;

import java.util.ArrayList;
import java.util.List;



public class TeamBuilder {

    private String name = "Ireland";

    private String code = "IRE";

    private List<Player> players = new ArrayList<>();

    public TeamBuilder withName(String name){
        this.name = name;
        return this;
    }

    public TeamBuilder withCode(String code){
        this.code = code;
        return this;
    }

    public TeamBuilder withPlayers(List<Player> players){
        this.players = players;
        return this;
    }

    public Team build(){
        return new Team(name, code, players);
    }
}
