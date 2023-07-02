package com.fbs.airline.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbs.airline.exception.LocationException;
import com.fbs.airline.model.Location;
import com.fbs.airline.repository.LocationRepository;

@Service
public class LocationService implements ILocationService {

	@Autowired
	LocationRepository locationRepository;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public Location addLocation(Location location) throws LocationException {
		if (locationRepository.existsByPlace(location.getPlace())) {
			String message = "Error: Location by this name already exists";
			logger.error(message);
			throw new LocationException(message);
		} else {
			Location addedLocation = locationRepository.save(location);
			logger.info("location added successfully");
			return addedLocation;
		}
	}

	@Override
	public List<Location> getAllLocations() throws LocationException {
		List<Location> locations = locationRepository.findAll();

		if (locations.isEmpty()) {
			String message = "Error: No Locations Found";
			logger.error(message);
			throw new LocationException(message);
		} else {
			logger.info("Locations retrieved successfully. {} items found", locations.size());
			return locations;
		}
	}

	@Override
	public Location updateLocation(Location location) throws LocationException {
		if (!locationRepository.existsById(location.getId())) {
			String message = "Error: Location doesn't exist";
			logger.error(message);
			throw new LocationException(message);
		} else {
			Location updated = locationRepository.save(location);
			logger.info("location updated successfully");
			return updated;
		}
	}

	@Override
	public boolean deleteLocation(Location location) throws LocationException {
		if (!locationRepository.existsById(location.getId())) {
			String message = "Error: Location doesn't exist";
			logger.error(message);
			throw new LocationException(message);
		} else {
			locationRepository.deleteById(location.getId());
			logger.info("location deleted successfully");
			return true;
		}
	}

}
