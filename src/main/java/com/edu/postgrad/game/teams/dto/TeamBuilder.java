package com.edu.postgrad.game.teams.dto;

import java.util.Arrays;
import java.util.List;



public class TeamBuilder {

    private String name = "Ireland";

    private String code = "IRE";

    private List<Player> players = Arrays.asList(/*new PlayerBuilder().build()*/);

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
