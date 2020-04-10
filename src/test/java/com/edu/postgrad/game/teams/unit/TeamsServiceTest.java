package com.edu.postgrad.game.teams.unit;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import com.edu.postgrad.game.teams.dao.TeamRepository;
import com.edu.postgrad.game.teams.dto.Player;
import com.edu.postgrad.game.teams.dto.PlayerBuilder;
import com.edu.postgrad.game.teams.dto.Team;
import com.edu.postgrad.game.teams.dto.TeamBuilder;
import com.edu.postgrad.game.teams.exception.PlayerException;
import com.edu.postgrad.game.teams.exception.TeamException;
import com.edu.postgrad.game.teams.service.TeamService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;
import static java.util.Optional.of;
import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TeamsServiceTest {

    TeamService teamService = new TeamService();
    TeamRepository teamRepository = Mockito.mock(TeamRepository.class);

    @BeforeEach
    public void setUp() {
        teamService.setTeamRepository(teamRepository);
    }

    @Test
    public void testTeamRepositoryIsInvoked(){
        Team team = new TeamBuilder().build();
        when(teamRepository.save(team)).thenReturn(team);

        teamService.saveTeam(team);
        verify(teamRepository, new Times(1)).save(team);
    }

    @Test
    public void getAllTeams(){
        List<Team> expectedTeams = Lists.newArrayList(new TeamBuilder().build());
        when(teamRepository.findAll()).thenReturn(expectedTeams);

        List<Team> actualTeams = teamService.getAllTeams();
        Assertions.assertEquals(expectedTeams.size(), actualTeams.size());
    }

    @Test
    public void testFindValidTeam(){
        Team expectedTeam = new TeamBuilder().build();
        when(teamRepository.findById(1L)).thenReturn(ofNullable(expectedTeam));

        Team actualTeam = teamService.findById(1L);
        Assertions.assertEquals(expectedTeam.getName(), actualTeam.getName());
        Assertions.assertEquals(expectedTeam.getCode(), actualTeam.getCode());
    }

    @Test
    public void invalidTeamThrowsException(){
        when(teamRepository.findById(1L)).thenReturn(ofNullable(null));

        assertThrows(TeamException.class, () ->  teamService.findById(1L));
    }

}
