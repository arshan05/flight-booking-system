package com.fbs.airline.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import com.fbs.airline.exception.ScheduleException;
import com.fbs.airline.model.Schedule;
import com.fbs.airline.service.AuthService;
import com.fbs.airline.service.IScheduleService;

class ScheduleControllerTest {


	@Mock
	IScheduleService scheduleService;

	@Mock
	AuthService authService;

	@InjectMocks
	ScheduleController scheduleController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
    void testAddSchedule() throws ScheduleException {
        // Mock the AuthService to return true for session validity
        when(authService.isSessionValid(anyString())).thenReturn(true);

        Schedule schedule = new Schedule();
        when(scheduleService.addSchedule(any(Schedule.class))).thenReturn(schedule);

        ResponseEntity<Schedule> response = scheduleController.addSchedule("dummy-cookie", schedule);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(schedule, response.getBody());

    }

	@Test
    void testGetAllSchedules() throws ScheduleException {
        when(authService.isSessionValid(anyString())).thenReturn(true);

        List<Schedule> schedules = new ArrayList<>();
        when(scheduleService.getAllSchedules()).thenReturn(schedules);

        ResponseEntity<List<Schedule>> response = scheduleController.getAllSchedules("dummy-cookie");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(schedules, response.getBody());

    }

	@Test
	public void testDeleteSchedule() throws ScheduleException {
		Schedule schedule = new Schedule();

		when(scheduleService.deleteSchedule(schedule)).thenReturn(true);
		when(authService.isSessionValid(anyString())).thenReturn(true);

		ResponseEntity<String> response = scheduleController.deleteAirine("dummy-cookie", schedule);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Successfully Deleted", response.getBody());
	}

	@Test
	public void testUpdateSchedule() throws ScheduleException {
		when(authService.isSessionValid("dummy-cookie")).thenReturn(true);

        Schedule schedule = new Schedule();
        Date istDateStart = new Date();
        Date istDateEnd = new Date();
        schedule.setStartTime(istDateStart);
        schedule.setEndTime(istDateEnd);

        Date utcDateStart = new Date(istDateStart.getTime() + TimeZone.getTimeZone("UTC").getOffset(istDateStart.getTime()));
        Date utcDateEnd = new Date(istDateEnd.getTime() + TimeZone.getTimeZone("UTC").getOffset(istDateEnd.getTime()));

        when(scheduleService.updateSchedule(any(Schedule.class))).thenReturn(schedule);

        ResponseEntity<Schedule> response = scheduleController.updateSchedule("dummy-cookie", schedule);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(schedule, response.getBody());
	}

	@Test
    void testAddScheduleThrowsUnauthorizedException() throws ScheduleException {
        when(authService.isSessionValid(anyString())).thenReturn(false);

        Schedule schedule = new Schedule();

        assertThrows(ResponseStatusException.class, () -> {
            scheduleController.addSchedule("dummy-cookie", schedule);
        });

    }

	@Test
    void testGetAllSchedulesThrowsUnauthorizedException() throws ScheduleException {
        when(authService.isSessionValid(anyString())).thenReturn(false);
        
        assertThrows(ResponseStatusException.class, () -> {
            scheduleController.getAllSchedules("dummy-cookie");
        });

    }

	@Test
	public void testDeleteScheduleThrowsUnauthorizedException() throws ScheduleException {
		Schedule schedule = new Schedule();

		when(authService.isSessionValid(anyString())).thenReturn(false);
		assertThrows(ResponseStatusException.class, () -> {
			scheduleController.deleteAirine("dummy-cookie", schedule);
		});
	}

	@Test
	public void testUpdateScheduleThrowsUnauthorizedException() throws ScheduleException {
		Schedule schedule = new Schedule();

		when(authService.isSessionValid(anyString())).thenReturn(false);
		assertThrows(ResponseStatusException.class, () -> {
			scheduleController.updateSchedule("dummy-cookie", schedule);
		});
	}

}
