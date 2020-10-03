package com.example.bpdts.BPDTSTEST;

import com.example.bpdts.BPDTSTEST.models.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

    User user;

    @Before
    public void setUp() {
        user = new User ("Andrew", "Watson", "awatson.work@gmail.com", "1.234.567.89", 55.1270, 1.5103);
    }

    @Test
    public void canGetFirstName() {
        assertEquals("Andrew", user.getFirstName());
    }


}