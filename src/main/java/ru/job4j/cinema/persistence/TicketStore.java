package ru.job4j.cinema.persistence;

import org.springframework.stereotype.Repository;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.model.User;

import java.util.Collection;
import java.util.List;

@Repository
public class TicketStore {
    public Collection<Ticket> getAllByUser(User user) {
        return List.of();
    }
}
