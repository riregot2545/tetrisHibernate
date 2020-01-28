package com.tetris.db.repositories;

import com.tetris.db.ConnectionFactory;
import org.hibernate.SessionFactory;

public interface Repository {
    SessionFactory sessionFactory =  ConnectionFactory.sessionFactory;
}
