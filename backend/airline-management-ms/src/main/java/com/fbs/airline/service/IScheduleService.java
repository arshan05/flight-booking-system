package com.fbs.airline.service;

import java.util.List;

import com.fbs.airline.exception.ScheduleException;
import com.fbs.airline.model.Schedule;

public interface IScheduleService {	
	Schedule addSchedule(Schedule schedule) throws ScheduleException;
	List<Schedule> getAllSchedules() throws ScheduleException;
	Schedule updateSchedule(Schedule schedule) throws ScheduleException;
	boolean deleteSchedule(Schedule schedule) throws ScheduleException;
	
	
	
}
