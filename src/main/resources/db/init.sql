CREATE TABLE IF NOT EXISTS game
(
    game_id SERIAL,
    state   VARCHAR(20) NOT NULL DEFAULT NULL,
    PRIMARY KEY (game_id)
);

CREATE TABLE IF NOT EXISTS figure_type
(
    figure_type_id   SERIAL,
    figure_structure VARCHAR(150) NOT NULL,
    PRIMARY KEY (figure_type_id)
);

CREATE TABLE IF NOT EXISTS move
(
    move_id   SERIAL,
    game_id   INT         NOT NULL,
    move_event_type VARCHAR(20) NOT NULL,
    PRIMARY KEY (move_id),
    FOREIGN KEY (game_id) REFERENCES game (game_id)
);

CREATE TABLE IF NOT EXISTS figure
(
    figure_type_id INT,
    game_id        INT,
    FOREIGN KEY (game_id) REFERENCES game (game_id),
    FOREIGN KEY (figure_type_id) REFERENCES figure_type (figure_type_id)
);

