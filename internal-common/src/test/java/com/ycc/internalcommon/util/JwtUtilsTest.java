package com.ycc.internalcommon.util;

import com.ycc.internalcommon.constant.UserIdentity;
import com.ycc.internalcommon.dto.TokenResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JwtUtilsTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void generateToken() {
        String token = JwtUtils.generateToken("15872427308", UserIdentity.Passenger);
        System.out.println(token);
    }

    @Test
    public void parseToken() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwaG9uZSI6IjE1ODcyNDI3MzA4IiwiZXhwIjoxNjY5NTM0Mzc1fQ.1IPslrRGqb9_-dEwzpA5LgKzzW7zSwejQuiTEeOs7ps\n";
        TokenResult tokenResult = JwtUtils.parseToken(token);
        System.out.println(tokenResult);
    }
}