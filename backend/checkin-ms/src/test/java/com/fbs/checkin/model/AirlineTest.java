package com.fbs.checkin.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class AirlineTest {

    @Test
    void testConstructorAndGetters() {
        // Create a sample airline
        Airline airline = new Airline("1", "ABC", "Test Airline", null);

        // Verify the values are set correctly
        assertEquals("1", airline.getId());
        assertEquals("ABC", airline.getCode());
        assertEquals("Test Airline", airline.getAirlineName());
        assertNull(airline.getFlights());
    }

    @Test
    void testSetters() {
        // Create a sample airline
        Airline airline = new Airline();

        // Set the values using setters
        airline.setId("1");
        airline.setCode("ABC");
        airline.setAirlineName("Test Airline");
        airline.setFlights(null);

        // Verify the values are set correctly
        assertEquals("1", airline.getId());
        assertEquals("ABC", airline.getCode());
        assertEquals("Test Airline", airline.getAirlineName());
        assertNull(airline.getFlights());
    }

    @Test
    void testToString() {
        // Create a sample airline
        Airline airline = new Airline("1", "ABC", "Test Airline", null);

        // Verify the string representation
        String expectedString = "Airline(id=1, code=ABC, airlineName=Test Airline, flights=null)";
        assertEquals(expectedString, airline.toString());
    }

    @Test
    void testAllArgsConstructor() {
    
        Airline airline = new Airline("1", "ABC", "Test Airline", null);

        assertEquals("1", airline.getId());
        assertEquals("ABC", airline.getCode());
        assertEquals("Test Airline", airline.getAirlineName());
        assertNull(airline.getFlights());
    }

    @Test
    void testNoArgsConstructor() {
        Airline airline = new Airline();

        assertNotNull(airline);
    }
}
