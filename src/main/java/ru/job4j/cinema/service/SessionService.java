package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.model.Session;
import ru.job4j.cinema.persistence.SessionStore;

import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

@Service
public class SessionService {

    private final SessionStore sessionStore;

    public SessionService(SessionStore sessionStore) {
        this.sessionStore = sessionStore;
    }

    public Collection<Session> getAllFree() {
        int foo = 0;
        if (foo == 0) {
            return List.of(
                    new Session(0, "Кино1", LocalTime.of(10, 00)),
                    new Session(0, "Кино2", LocalTime.of(12, 00)));
        }
        return sessionStore.getAll();
    }
}
