package ru.job4j.cinema.persistence;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Repository;

@Repository
public class UserStore {

    private final BasicDataSource pool;

    public UserStore(BasicDataSource pool) {
        this.pool = pool;
    }
}
