package ru.job4j.cinema.persistence;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Repository;
import ru.job4j.cinema.model.Session;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.model.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public class TicketStore {

    private final BasicDataSource pool;

    public TicketStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public Ticket add(Ticket ticket) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(
                     "INSERT INTO ticket(film_id, row_, cell, user_id) VALUES (?, ?, ?, ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setInt(1, ticket.getSessionId());
            ps.setInt(2, ticket.getRow());
            ps.setInt(3, ticket.getCell());
            ps.setInt(4, ticket.getUserId());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    ticket.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticket;
    }

    public Collection<Ticket> getAll() {
        List<Ticket> allTickets = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM ticket")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    allTickets.add(new Ticket(it.getInt("id"),
                            it.getInt("film_id"),
                            it.getInt("row_"),
                            it.getInt("cell"),
                            it.getInt("user_id")
                            ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allTickets;
    }


    public Collection<Ticket> getAllByUser(User user) {
        List<Ticket> allTicketsByUser = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM ticket WHERE user_id = ?")
        ) {
            ps.setInt(1, user.getId());
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    allTicketsByUser.add(new Ticket(it.getInt("id"),
                            it.getInt("user_id"),
                            it.getInt("film_id"),
                            it.getInt("row_"),
                            it.getInt("cell")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allTicketsByUser;
    }


    public Collection<Ticket> getByRowAndSession(int session, int row) {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM ticket WHERE film_id = ? AND row_ = ?")
        ) {
            ps.setInt(1, session);
            ps.setInt(2, row);
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    tickets.add(new Ticket(it.getInt("id"),
                            it.getInt("user_id"),
                            it.getInt("film_id"),
                            it.getInt("row_"),
                            it.getInt("cell")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tickets;
    }
}

