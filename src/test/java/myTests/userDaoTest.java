package myTests;

import dao.UserDao;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;



public class userDaoTest {

    private UserDao myUserDao;

    private int expected;

    @BeforeEach
    public void setUp(){
        myUserDao = new UserDao();
        expected = 5;

    }

    @Test
    public void returnTheNumberFive(){

        int actual = myUserDao.getIntegerValue();

        Assertions.assertEquals(expected, actual);
    }


}
