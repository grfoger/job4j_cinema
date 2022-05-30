package ru.job4j.cinema.persistence;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Repository;
import ru.job4j.cinema.model.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class SessionStore {

    private final BasicDataSource pool;

    public SessionStore(BasicDataSource pool) {
        this.pool = pool;
    }
    public Collection<Session> getAll() {
        List<Session> films = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM films")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    films.add(new Session(it.getInt("id"),
                            it.getString("name"),
                            it.getTime("time").toLocalTime()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return films;
    }

    public Session getById(int id) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM films WHERE id = ?")
        ) {
            ps.setInt(1, id);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    return new Session(it.getInt("id"),
                            it.getString("name"),
                            it.getTime("time").toLocalTime());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
