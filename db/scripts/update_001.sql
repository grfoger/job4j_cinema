
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR NOT NULL,
                       email VARCHAR NOT NULL UNIQUE,
                       phone VARCHAR NOT NULL UNIQUE,
                       password VARCHAR NOT NULL
);

CREATE TABLE films (
                          id SERIAL PRIMARY KEY,
                          name TEXT,
                          time TIME
);

CREATE TABLE ticket (
                        id SERIAL PRIMARY KEY,
                        film_id INT NOT NULL REFERENCES films(id),
                        row_ INT NOT NULL,
                        cell INT NOT NULL,
                        user_id INT NOT NULL REFERENCES users(id),
                        UNIQUE(film_id, row_, cell)
);



INSERT INTO films(name, time) VALUES ('Как я отколошматил лося одной левой', '12:00');
INSERT INTO films(name, time) VALUES ('Мячом по кумполу', '19:00');
INSERT INTO films(name, time) VALUES ('Механический Дед Мороз м/ф', '20:00');