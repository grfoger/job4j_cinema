package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.model.Session;
import ru.job4j.cinema.model.Ticket;
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

        return sessionStore.getAll();
    }

}
