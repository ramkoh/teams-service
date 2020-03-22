package com.edu.postgrad.game.teams.unit;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Stream;

import com.edu.postgrad.game.teams.dao.PlayerRepository;
import com.edu.postgrad.game.teams.dto.Player;
import com.edu.postgrad.game.teams.dto.PlayerBuilder;
import com.edu.postgrad.game.teams.exception.InvalidPlayerException;
import com.edu.postgrad.game.teams.exception.PlayerException;
import com.edu.postgrad.game.teams.service.PlayerService;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import org.mockito.internal.verification.Times;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PlayersTest {

    PlayerService playerService = new PlayerService();

    PlayerRepository playerRepository = mock(PlayerRepository.class);

    private static Validator validator;

    @BeforeAll
    public static void classSetUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

    }

    @BeforeEach
    public void setUp() {
        playerService.setPlayerRepository(playerRepository);
    }

    @Test
    public void testPlayerRepositoryIsInvoked(){
        Player player = new PlayerBuilder().build();
        when(playerRepository.save(player)).thenReturn(player);
        playerService.savePlayer(player);
        verify(playerRepository, new Times(1)).save(player);
    }

    @DisplayName(" Null player instance should throw an exception")
    @Test
    public void testNullPlayer(){
        assertThrows(PlayerException.class, () -> { playerService.savePlayer(null);});
    }

    @DisplayName("Invalid First Name should throw an exception")
    @ParameterizedTest(name = "{index} {1} with arguments name={0}")
    @MethodSource("firstNameProvider")
    public void testInvalidFirstName(String name, String description, String expectedErrorMessage){
        Player player = new PlayerBuilder().withFirstName(name).build();

        Set<ConstraintViolation<Player>> violations = validator.validate(player);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());

        ConstraintViolation constraintViolation = violations.stream().findFirst().get();
        assertEquals("firstName", constraintViolation.getPropertyPath().toString());
        assertEquals(expectedErrorMessage, constraintViolation.getMessage());
    }
    private static Stream<Arguments> firstNameProvider() {
        return Stream.of(
                Arguments.of(null, "Test for negative number", "must not be null"),
                Arguments.of("", "Min size validation for empty string", "First Name must be at least 5 characters long"),
                Arguments.of("abcd", "Min size validation for 4 chars long name", "First Name must be at least 5 characters long")
        );
    }
    @DisplayName("Invalid Last Name should throw an exception")
    @ParameterizedTest(name = "{index} {1} with arguments name={0}")
    @MethodSource("lastNameProvider")
    public void testInvalidLastName(String name, String description, String expectedErrorMessage){
        Player player = new PlayerBuilder().withLastName(name).build();

        Set<ConstraintViolation<Player>> violations = validator.validate(player);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());

        ConstraintViolation constraintViolation = violations.stream().findFirst().get();
        assertEquals("lastName", constraintViolation.getPropertyPath().toString());
        assertEquals(expectedErrorMessage, constraintViolation.getMessage());
    }
    private static Stream<Arguments> lastNameProvider() {
        return Stream.of(
                Arguments.of(null, "Test for negative number", "must not be null"),
                Arguments.of("", "Min size validation for empty string", "Last Name must be at least 5 characters long"),
                Arguments.of("abcd", "Min size validation for 4 chars long name", "Last Name must be at least 5 characters long")
        );
    }

    @DisplayName("Invalid Jersey Number should throw an exception")
    @ParameterizedTest(name = "{index} {1} with arguments jerseyNumber={0}")
    @MethodSource("jerseyNumberProvider")
    public void testNegativeJerseyNumberThrowsException(int jerseyNumber, String description){
        Player player = new PlayerBuilder().withJerseyNumber(jerseyNumber).build();
        Set<ConstraintViolation<Player>> violations = validator.validate(player);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());

        ConstraintViolation constraintViolation = violations.stream().findFirst().get();
        assertEquals("jerseyNumber", constraintViolation.getPropertyPath().toString());
        assertEquals("must be greater than 0", constraintViolation.getMessage());
    }
    private static Stream<Arguments> jerseyNumberProvider() {
        return Stream.of(
                Arguments.of(-1, "Test for negative number"),
                Arguments.of(0, "Test for zero")
        );
    }

    @DisplayName("Invalid Date of birth should throw an exception")
    @ParameterizedTest(name = "{index} {1} with arguments dob={0}")
    @MethodSource("dobProvider")
    void dateOfBirthTest(String dob) {
        Player player = new PlayerBuilder().withDob(LocalDate.parse(dob)).build();
        assertThrows(InvalidPlayerException.class, () -> { playerService.savePlayer(player);});
    }
    private static Stream<Arguments> dobProvider() {
        return Stream.of(
                Arguments.of("2021-03-21", "Test for future date"),
                Arguments.of("2019-03-21", "Test for date of last year"),
                Arguments.of("2009-03-21", "Boundary value test for exact 12th year")
        );
    }

    @DisplayName("Same First and Last name should throw an exception")
    @ParameterizedTest(name = "{index} {2} with arguments first name={0}, last name={1}")
    @MethodSource("dobProvider")
    public void testFirstAndLastNameShouldNotBeSame(String firstName, String lastName){
        Player player = new PlayerBuilder().withFirstName("ABC")
                .withLastName("ABC")
                .build();
        assertThrows(InvalidPlayerException.class, () -> { playerService.savePlayer(player);});
    }

    private static Stream<Arguments> firstAndLastNameProvider() {
        return Stream.of(
                Arguments.of("ABC", "ABC", "All capitals"),
                Arguments.of("abc", "abc", "All smalls"),
                Arguments.of("Abc", "aBC", "Mixed case")
        );
    }

}
