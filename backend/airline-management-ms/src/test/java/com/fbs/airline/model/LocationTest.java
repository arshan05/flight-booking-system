package com.fbs.airline.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class LocationTest {

    @Test
    void testConstructorAndGetters() {
        Location location = new Location("1","Bengaluru", "Karnataka", "India");

        assertEquals("Bengaluru", location.getPlace());
        assertEquals("Karnataka", location.getState());
        assertEquals("India", location.getCountry());
    }

    @Test
    void testSetters() {
        Location location = new Location();

        location.setPlace("Bengaluru");
        location.setState("Karnataka");
        location.setCountry("India");

        assertEquals("Bengaluru", location.getPlace());
        assertEquals("Karnataka", location.getState());
        assertEquals("India", location.getCountry());
    }

    @Test
    void testToString() {
    	Location location = new Location("1","Bengaluru", "Karnataka", "India");
    	
        String expectedString = "Location(id=1, place=Bengaluru, state=Karnataka, country=India)";
        assertEquals(expectedString, location.toString());
    }

    @Test
    void testAllArgsConstructor() {
    	Location location = new Location("1","Bengaluru", "Karnataka", "India");
    	
    	assertEquals("Bengaluru", location.getPlace());
        assertEquals("Karnataka", location.getState());
        assertEquals("India", location.getCountry());
    }

    @Test
    void testNoArgsConstructor() {
        Location location = new Location();

        assertNotNull(location);
    }
}
