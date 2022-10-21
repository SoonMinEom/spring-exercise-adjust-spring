package com.likelion.dao;

import com.likelion.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory.class)
class UserDaoTest {

    @Autowired
    ApplicationContext context;

    UserDao userDao;
    User user1;
    User user2;
    User user3;

    @BeforeEach
    void before() throws SQLException {
        this.userDao = context.getBean("awsUserDao", UserDao.class);
        userDao.deleteAll();
        this.user1 = new User("1","a","aaaa");
        this.user2 = new User("2","b","bbbb");
        this.user3 = new User("3","c","cccc");
    }

    @Test
    void addAndGet() throws SQLException {

        userDao.add(user1);
        User user = userDao.get(user1.getId());

        assertEquals(user1.getName(), user.getName());
        assertEquals(user1.getPassword(), user.getPassword());

        assertThrows(EmptyResultDataAccessException.class, ()-> {
            userDao.get("30");
        });
    }

    @Test
    void deleteAll() throws SQLException {
        userDao.deleteAll();
        assertEquals(0,userDao.getCount());
    }

    @Test
    void getCount() throws SQLException {
        userDao.add(user1);
        assertEquals(1,userDao.getCount());

        userDao.add(user2);
        assertEquals(2,userDao.getCount());

        userDao.add(user3);
        assertEquals(3,userDao.getCount());

    }
}