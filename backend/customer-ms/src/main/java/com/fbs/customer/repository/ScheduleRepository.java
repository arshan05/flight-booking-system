package com.fbs.customer.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.fbs.customer.model.Schedule;

public interface ScheduleRepository extends MongoRepository<Schedule, String>{
//
//	@Query("{'boarding.location.place':?0, 'desstination.location.place':?1}")
//	List<Schedule> findByPlaceAndPlaceAndStartTimeGreaterThanEqual(String start, String destination, Date date);
	

//	@Query("["
//			+ "  {$lookup:"
//			+ "      {"
//			+ "        from: 'airports',"
//			+ "        localField: 'boarding.$id',"
//			+ "        foreignField: '_id',"
//			+ "        as: 'boardingAirport',"
//			+ "      },"
//			+ "  },"
//			+ "  {"
//			+ "    $match:"
//			+ "      {"
//			+ "        'boardingAirport.location.place':"
//			+ "          'Mumbai',"
//			+ "      },"
//			+ "  },"
//			+ "]")
//	List<Schedule> findByBoarding(String start);

//	List<Schedule> findSchedulesByBoarding_Location_PlaceAndDestination_Location_Place(String start);

}
