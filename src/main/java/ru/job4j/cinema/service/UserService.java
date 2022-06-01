package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.model.Session;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.persistence.SessionStore;
import ru.job4j.cinema.persistence.TicketStore;
import ru.job4j.cinema.persistence.UserStore;

import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final TicketStore ticketStore;
    private final UserStore userStore;
    private final SessionStore sessionStore;

    public UserService(TicketStore ticketStore, UserStore userStore, SessionStore sessionStore) {
        this.ticketStore = ticketStore;
        this.userStore = userStore;
        this.sessionStore = sessionStore;
    }

    public Collection<Ticket> getUserTickets(User user) {
        int foo = 0;
        if (foo == 0) {
            return List.of(
                    new Ticket(0, user.getId(), 1, 1, 1),
                    new Ticket(0, user.getId(), 1, 1, 1));
        }
        return ticketStore.getAllByUser(user);
    }

    public Optional<User> add(User user) {
        return userStore.addUser(user);
    }

}
