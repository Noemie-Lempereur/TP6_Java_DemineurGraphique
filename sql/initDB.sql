DROP DATABASE IF EXISTS minesweeper;

CREATE DATABASE minesweeper;

USE minesweeper;

CREATE TABLE player(
    id_player  INT auto_increment,
    nom VARCHAR(300) NOT NULL ,
    password VARCHAR(300),
    PRIMARY KEY (id_player)
);

CREATE TABLE game(
    id_game  INT auto_increment,
    score INT NOT NULL,
    id_player INT NOT NULL ,
    PRIMARY KEY (id_game),
    CONSTRAINT FK_playerGame FOREIGN KEY (id_player) REFERENCES player(id_player)
);

INSERT INTO `minesweeper`.`player` (`nom`,`password`) VALUES ("Guest","");
