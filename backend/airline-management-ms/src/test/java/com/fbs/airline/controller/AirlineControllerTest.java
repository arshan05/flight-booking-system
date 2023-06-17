package com.fbs.airline.controller;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fbs.airline.service.AirlineService;
import com.fbs.airline.service.AuthService;

class AirlineControllerTest {
	
	@Mock
    private AirlineService airlineService;

    @Mock
    private AuthService authService;

    @InjectMocks
    private AirlineController airlineController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

	@Test
	void testAddAirline() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAllAirlines() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteAirine() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateAirline() {
		fail("Not yet implemented");
	}

}
