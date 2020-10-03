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

    @Test
    public void canGetLastName() {
        assertEquals("Watson", user.getLastName());
    }

    @Test
    public void canGetEmail() {
        assertEquals("awatson.work@gmail.com", user.getEmail());
    }

    @Test
    public void canGetIpAddress() {
        assertEquals("1.234.567.89", user.getIpAddress());
    }

    @Test
    public void canGetLatitude() {
        assertEquals(55.1270, user.getLatitude(), 0);
    }

    @Test
    public void canGetLongitude() {
        assertEquals(1.5103, user.getLongitude(), 0);
    }

}
