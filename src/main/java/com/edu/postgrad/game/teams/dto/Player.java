package com.edu.postgrad.game.teams.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@ApiModel(description = "Entity to hold Player data")
@Entity
@Table(name = "players")
@Component
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty("First name of player")
    @NotNull
    @Size(min=5, message="First Name must be at least 5 characters long")
    @Column(name = "first_name")
    private String firstName;

    @ApiModelProperty("Last name of player")
    @NotNull
    @Size(min=5, message="Last Name must be at least 5 characters long")
    @Column(name = "last_name")
    private String lastName;

    @ApiModelProperty("Last name of player")
    @Column(name = "jersey_number")
    @Positive
    private int jerseyNumber;

    @ApiModelProperty("Date of birth of player")
    @Column( columnDefinition = "DATE")
    @NotNull
    @Past
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyy")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dob;

    @ApiModelProperty("Position of player")
    @Enumerated(EnumType.STRING)
    @Column(name = "player_position")
    private Position position;

    @ApiModelProperty("Age of player")
    @Transient
    private int age;

    public Player(){
    }

    protected Player(String firstName, String lastName, int jerseyNumber, LocalDate dob, Position position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jerseyNumber = jerseyNumber;
        this.dob = dob;
        this.position = position;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}
