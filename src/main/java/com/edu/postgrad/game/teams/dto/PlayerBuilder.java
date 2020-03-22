package com.edu.postgrad.game.teams.dto;

import java.time.LocalDate;

public class PlayerBuilder {
    private String firstName = "Raman";

    private String lastName = "Arora";

    private int jerseyNumber = 99;

    private LocalDate dob = LocalDate.of(1990,1,1);

    private Position position = Position.DEFENDER;

    public PlayerBuilder withFirstName(final String firstName){
        this.firstName = firstName;
        return this;
    }

    public PlayerBuilder withLastName(final String lastName){
        this.lastName = lastName;
        return this;
    }

    public PlayerBuilder withJerseyNumber(final int jerseyNumber){
        this.jerseyNumber = jerseyNumber;
        return this;
    }

    public PlayerBuilder withDob(final LocalDate dob){
        this.dob = dob;
        return this;
    }

  /*  public PlayerBuilder withPosition(final Position position){
        this.position = position;
        return this;
    }*/

    public Player build() {
        return new Player(firstName, lastName, jerseyNumber, dob, position);
    }
}
