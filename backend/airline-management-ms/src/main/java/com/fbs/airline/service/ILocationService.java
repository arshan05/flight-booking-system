package com.fbs.airline.service;

import java.util.List;

import com.fbs.airline.exception.LocationException;
import com.fbs.airline.model.Location;

public interface ILocationService {
	List<Location> getAllLocations() throws LocationException;
	Location addLocation(Location location) throws LocationException;
	Location updateLocation(Location location) throws LocationException;
	boolean deleteLocation(Location location) throws LocationException;
}
