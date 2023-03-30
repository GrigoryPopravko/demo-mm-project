package by.paprauka.dao;

import by.paprauka.database.dao.UserDao;
import by.paprauka.database.entity.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserDaoTest {

    @Test
    public void getDummy() {
        UserDao userDao = UserDao.getInstance();
        assertEquals(User.builder()
                        .name("Bob")
                        .surname("Smith")
                        .build(),
                userDao.getDummy());
    }
}