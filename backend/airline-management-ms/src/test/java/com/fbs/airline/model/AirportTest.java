package com.fbs.airline.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class AirportTest {

    @Test
    void testConstructorAndGetters() {
    	Airport airport = new Airport("1", null, "BLR", "Kempegowda International Airport",null);

        assertEquals("1", airport.getId());
        assertEquals("BLR", airport.getCode());
        assertNull(airport.getLocation());
        assertEquals("Kempegowda International Airport", airport.getAirportName());
        assertNull(airport.getFlights());
    }

    @Test
    void testSetters() {
        Airport airport = new Airport();

        airport.setId("1");
        airport.setCode("ABC");
        airport.setLocation(null);
        airport.setAirportName("Test Airport");
        airport.setFlights(null);

        assertEquals("1", airport.getId());
        assertEquals("ABC", airport.getCode());
        assertNull(airport.getLocation());
        assertEquals("Test Airport", airport.getAirportName());
        assertNull(airport.getFlights());
    }

    @Test
    void testToString() {
        Airport airport = new Airport("1", null, "BLR", "Kempegowda International Airport",null);

        String expectedString = "Airport(id=1, location=null, code=BLR, airportName=Kempegowda International Airport, flights=null)";
        assertEquals(expectedString, airport.toString());
    }

    @Test
    void testAllArgsConstructor() {
    	Airport airport = new Airport("1", null, "BLR", "Kempegowda International Airport",null);
    	
        assertEquals("1", airport.getId());
        assertNull(airport.getLocation());
        assertEquals("BLR", airport.getCode());
        assertEquals("Kempegowda International Airport", airport.getAirportName());
        assertNull(airport.getFlights());
    }

    @Test
    void testNoArgsConstructor() {
        Airport airport = new Airport();

        assertNotNull(airport);
    }
}

