CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR NOT NULL,
                       email VARCHAR NOT NULL UNIQUE,
                       phone VARCHAR NOT NULL UNIQUE
);

CREATE TABLE sessions (
                          id SERIAL PRIMARY KEY,
                          name TEXT,
                          time TIME
);

CREATE TABLE ticket (
                        id SERIAL PRIMARY KEY,
                        session_id INT NOT NULL REFERENCES sessions(id),
                        row INT NOT NULL,
                        cell INT NOT NULL,
                        user_id INT NOT NULL REFERENCES users(id)
);

INSERT INTO sessions(name, time) VALUES ('Как я отколошматил лося одной левой', '10:00');
INSERT INTO sessions(name, time) VALUES ('Мячом по кумполу', '12:00');
INSERT INTO sessions(name, time) VALUES ('Механический Дед Мороз м/ф', '14:00');
