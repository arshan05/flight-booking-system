//package com.fbs.airline.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.slf4j.Logger;
//
//import com.fbs.airline.exception.ScheduleException;
//import com.fbs.airline.model.Airline;
//import com.fbs.airline.model.Flight;
//import com.fbs.airline.model.Schedule;
//import com.fbs.airline.model.Status;
//import com.fbs.airline.proxy.FareProxy;
//import com.fbs.airline.repository.AirlineRepository;
//import com.fbs.airline.repository.FlightRepository;
//import com.fbs.airline.repository.ScheduleRepository;
//
//class ScheduleServiceTest {
//
//    @Mock
//    private AirlineRepository airlineRepository;
//
//    @Mock
//    private FlightRepository flightRepository;
//
//    @Mock
//    private ScheduleRepository scheduleRepository;
//
//    @Mock
//    private FareProxy fareProxy;
//
//    @Mock
//    private Logger logger;
//
//    @InjectMocks
//    private ScheduleService scheduleService;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testAddSchedule_WithExistingSchedule_ThrowsScheduleException() throws ScheduleException {
//    	Airline airline = new Airline("1", "ABC", "Test Airline", null);
//		Flight flight = new Flight("1", "fl01", airline, 90, 6, null, null);
//		
//		Schedule schedule = new Schedule("1", flight, null, null,new Date("23/12/2023"),new Date("23/12/2023"),Status.ONTIME,null,10000);
//		
//        List<Schedule> existingSchedules = new ArrayList<>();
//        existingSchedules.add(schedule);
//        flight.setSchedules(existingSchedules);
//
//        Schedule newSchedule = new Schedule("2", flight, null, null,new Date("23/12/2023"),new Date("23/12/2023"),Status.ONTIME,null,10000);
//
//        when(flightRepository.findById(flight.getId())).thenReturn(java.util.Optional.of(flight));
//        when(scheduleRepository.existsById(schedule.getId())).thenReturn(true);
//
//        // Act and Assert
//        assertThrows(ScheduleException.class, () -> scheduleService.addSchedule(newSchedule));
//        verify(flightRepository).findById(flight.getId());
//        verify(scheduleRepository).existsById(schedule.getId());
//        verify(logger).error("Error: Flight is already scheduled during this period");
//    }
//
//    @Test
//    public void testAddSchedule_WithNonExistingSchedule_Successful() throws ScheduleException {
//        // Arrange
//    	Airline airline = new Airline("1", "ABC", "Test Airline", null);
//		Flight flight = new Flight("1", "fl01", airline, 90, 6, null, null);
//		
//		Schedule schedule = new Schedule("1", flight, null, null,new Date("23/12/2023"),new Date("23/12/2023"),Status.ONTIME,null,10000);
//
//        when(flightRepository.findById(flight.getId())).thenReturn(java.util.Optional.of(flight));
//        when(scheduleRepository.existsById(schedule.getId())).thenReturn(false);
//
//        // Act
//        Schedule result = scheduleService.addSchedule(schedule);
//
//        // Assert
//        verify(flightRepository).findById(flight.getId());
//        verify(scheduleRepository).existsById(schedule.getId());
//        verify(fareProxy).setFareForEachSeat(schedule);
//        verify(scheduleRepository).save(schedule);
//        verify(flightRepository).save(flight);
//        verify(logger).info("schedule added successfully");
//        assertEquals(schedule, result);
//    }
//}
