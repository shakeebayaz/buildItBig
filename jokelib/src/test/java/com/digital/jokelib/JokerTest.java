package com.digital.jokelib;

import junit.framework.TestCase;


public class JokerTest extends TestCase {

    public void testGetJoke() throws Exception {
        com.digital.jokelib.Joker joker = new com.digital.jokelib.Joker();
        assertNotNull(joker.getJoke());
    }
}