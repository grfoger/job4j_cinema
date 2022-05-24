package ru.job4j.cinema.model;

import java.time.LocalTime;

public class Session {

    private int id;
    private String name;
    private LocalTime time;

    public Session() {
    }

    public Session(String name, LocalTime time) {
        this.name = name;
        this.time = time;
    }

    public Session(int id, String name, LocalTime time) {
        this.id = id;
        this.name = name;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Session session = (Session) o;

        return id == session.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
