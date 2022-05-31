package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.model.Session;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.persistence.SessionStore;
import ru.job4j.cinema.persistence.TicketStore;

import java.time.LocalTime;
import java.util.*;

@Service
public class SessionService {

    private final SessionStore sessionStore;
    private final TicketStore ticketStore;

    private static final int HALL_ROWS = 12;
    private static final int HALL_CELLS = 10;



    public SessionService(SessionStore sessionStore,  TicketStore ticketStore) {
        this.sessionStore = sessionStore;
        this.ticketStore = ticketStore;
    }

    public Collection<Session> getAll() {
        return sessionStore.getAll();
    }

    public Session getSessionById(int id) {
        return sessionStore.getById(id);
    }

    public Ticket addTicket(Ticket ticket) {
        return ticketStore.add(ticket);
    }

    public Ticket getTicketById(int id) {
        return ticketStore.getById(id);
    }

    public List<Integer> rows() {
        List<Integer> rows = new ArrayList<>();
        for (int i = 0; i < HALL_ROWS; i++) {
            rows.add(i + 1);
        }
        return rows;
    }

    public List<Integer> cells() {
        List<Integer> rows = new ArrayList<>();
        for (int i = 0; i < HALL_CELLS; i++) {
            rows.add(i + 1);
        }
        return rows;
    }


    public Collection<Ticket> getFreeTickets() {
        List<Ticket> freeTickets = new ArrayList<>();
        return freeTickets;
    }

}
