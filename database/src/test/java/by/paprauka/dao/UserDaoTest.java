package by.paprauka.dao;

import by.paprauka.entity.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserDaoTest {

    @Test
    public void getDummy() {
        UserDao userDao = UserDao.getInstance();
        assertEquals(new User("Bob", "Smith"), userDao.getDummy());
    }
}