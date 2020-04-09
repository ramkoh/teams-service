insert into players (id, first_name, last_name, jersey_number, player_position, dob) values (1, 'Lionel', 'Messi', '10', 'FORWARD', '1987-06-24');
insert into players (id, first_name, last_name, jersey_number, player_position, dob) values (2, 'Cristiano', 'Ronaldo', '20', 'FORWARD', '1985-02-05');
insert into players (id, first_name, last_name, jersey_number, player_position, dob) values (3, 'Xavi', 'Creus', '21', 'MID_FIELDER', '1980-01-25');
insert into players (id, first_name, last_name, jersey_number, player_position, dob) values (4, 'Andrés', 'Iniesta', '32', 'FORWARD', '1984-05-11');
insert into players (id, first_name, last_name, jersey_number, player_position, dob) values (5, 'Zlatan', 'Ibrahimovic', '52', 'FORWARD', '1981-10-03');
insert into players (id, first_name, last_name, jersey_number, player_position, dob) values (6, 'Radamel', 'Falcao', '72', 'FORWARD', '1986-02-15');

--German players
insert into players (id, first_name, last_name, jersey_number, player_position, dob) values (7, 'Leroy', 'Sane' ,'10', 'FORWARD', '1996-01-11');
insert into players (id, first_name, last_name, jersey_number, player_position, dob) values (8, 'Timo', 'Werner' ,'10', 'FORWARD', '1996-03-06');
insert into players (id, first_name, last_name, jersey_number, player_position, dob) values (9, 'Thomas', 'Muller', '10', 'FORWARD', '1989-09-13');
insert into players (id, first_name, last_name, jersey_number, player_position, dob) values (10, 'İlkay','Gündoğan', '10', 'MID_FIELDER', '1990-10-24');
insert into players (id, first_name, last_name, jersey_number, player_position, dob) values (11, 'Toni','Kroos' ,'10', 'MID_FIELDER', '1990-01-04');
insert into players (id, first_name, last_name, jersey_number, player_position, dob) values (12, 'Sami','Khedira', '10', 'MID_FIELDER', '1987-04-04');
insert into players (id, first_name, last_name, jersey_number, player_position, dob) values (13, 'Nico','Schulz', '10', 'MID_FIELDER', '1993-04-01');
insert into players (id, first_name, last_name, jersey_number, player_position, dob) values (14, 'Matt','Hummels', '10', 'DEFENDER', '1998-12-16');
insert into players (id, first_name, last_name, jersey_number, player_position, dob) values (15, 'Jerome', 'Boateng', '10', 'DEFENDER', '1988-09-03');
insert into players (id, first_name, last_name, jersey_number, player_position, dob) values (16, 'Joshua', 'Kimmich', '10', 'DEFENDER', '1995-02-08');
insert into players (id, first_name, last_name, jersey_number, player_position, dob) values (17, 'Manuel', 'Neuer', '10', 'GOAL_KEEPER', '1986-03-27');

--Italian team
insert into players (id, first_name, last_name, jersey_number, player_position, dob) values (18, 'Lorenzo', 'Insigne', '10', 'FORWARD', '1991-06-04');
insert into players (id, first_name, last_name, jersey_number, player_position, dob) values (19, 'Ciro', 'Immobile', '17', 'FORWARD', '1990-02-20');
insert into players (id, first_name, last_name, jersey_number, player_position, dob) values (20, 'Federico', 'Chiesa', '14', 'FORWARD','1997-10-25');
insert into players (id, first_name, last_name, jersey_number, player_position, dob) values (21, 'Marco', 'Verratti', '6', 'MID_FIELDER', '1992-11-05');
insert into players (id, first_name, last_name, jersey_number, player_position, dob) values (22, 'Nicolo', 'Barella', '18', 'MID_FIELDER', '1997-02-07');
insert into players (id, first_name, last_name, jersey_number, player_position, dob) values (23, 'Jorginho', '', '8', 'MID_FIELDER', '1991-12-20');
insert into players (id, first_name, last_name, jersey_number, player_position, dob) values (24, 'Cristiano', 'Biraghi', '4', 'DEFENDER', '1992-09-01');
insert into players (id, first_name, last_name, jersey_number, player_position, dob) values (25, 'Alessandro', 'Florenzi', '16', 'DEFENDER','1991-03-11');
insert into players (id, first_name, last_name, jersey_number, player_position, dob) values (26, 'Giorgio', 'Chiellini', '3', 'DEFENDER', '1984-08-14');
insert into players (id, first_name, last_name, jersey_number, player_position, dob) values (27, 'Gianluigi', 'Donnarumma', '22', 'GOAL_KEEPER', '1999-02-25');
insert into players (id, first_name, last_name, jersey_number, player_position, dob) values (28, 'Leonardo', 'Bonucci', '19', 'DEFENDER', '1987-05-01');



insert into teams(id, name, code) values (1, 'Germany', 'GER');
insert into teams(id, name, code) values (2, 'Italy', 'ITA');
insert into teams(id, name, code) values (3, 'Switzerland', 'SUI');
insert into teams(id, name, code) values (4, 'Russia', 'RUS');
insert into teams(id, name, code) values (5, 'Norway', 'NOR');
insert into teams(id, name, code) values (6, 'Mexico', 'MEX');
insert into teams(id, name, code) values (7, 'Denmark', 'DEN');

-- German team
insert into teams_players (team_id, players_id) values (1, 7);
insert into teams_players (team_id, players_id) values (1, 8);
insert into teams_players (team_id, players_id) values (1, 9);
insert into teams_players (team_id, players_id) values (1, 10);
insert into teams_players (team_id, players_id) values (1, 11);
insert into teams_players (team_id, players_id) values (1, 12);
insert into teams_players (team_id, players_id) values (1, 13);
insert into teams_players (team_id, players_id) values (1, 14);
insert into teams_players (team_id, players_id) values (1, 15);
insert into teams_players (team_id, players_id) values (1, 16);
insert into teams_players (team_id, players_id) values (1, 17);

--Italin team
insert into teams_players (team_id, players_id) values (2, 18);
insert into teams_players (team_id, players_id) values (2, 19);
insert into teams_players (team_id, players_id) values (2, 20);
insert into teams_players (team_id, players_id) values (2, 21);
insert into teams_players (team_id, players_id) values (2, 22);
insert into teams_players (team_id, players_id) values (2, 23);
insert into teams_players (team_id, players_id) values (2, 24);
insert into teams_players (team_id, players_id) values (2, 25);
insert into teams_players (team_id, players_id) values (2, 26);
insert into teams_players (team_id, players_id) values (2, 27);
insert into teams_players (team_id, players_id) values (2, 28);


