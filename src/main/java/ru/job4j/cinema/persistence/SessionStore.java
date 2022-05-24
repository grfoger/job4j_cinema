package ru.job4j.cinema.persistence;

import org.springframework.stereotype.Repository;
import ru.job4j.cinema.model.Session;

import java.util.Collection;
import java.util.List;

@Repository
public class SessionStore {
    public Collection<Session> getAll() {
        return List.of();
    }

    public Session getById(int id) {
        return new Session();
    }
}
