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
        return ticketStore.getAllByUser(user);
    }

    public Optional<User> add(User user) {
        return userStore.addUser(user);
    }

    public Optional<User> findUserByEmailAndPass(String email, String password) {
        Optional<User> userDb = userStore.findUserBy(email);
        if (userDb.isPresent() && !password.equals(userDb.get().getPassword())) {
            return Optional.empty();
        }
        return userDb;
    }

}
