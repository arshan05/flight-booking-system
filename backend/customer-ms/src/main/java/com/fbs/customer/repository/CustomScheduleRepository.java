package com.fbs.customer.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import com.fbs.customer.model.Schedule;

@Repository
public class CustomScheduleRepository {

	@Autowired
    private MongoTemplate mongoTemplate;


    public List<Schedule> findFlightSchedules(String boardingLocation, String destinationLocation,Date startTime) {
    	
    	System.out.println(boardingLocation);
        LookupOperation boardingLookupOperation = LookupOperation.newLookup()
        		.from("airports")
                .localField("boarding.$id")
                .foreignField("_id")
                .as("boardingAirport");
        
        LookupOperation destinationLookupOperation = LookupOperation.newLookup()
        		.from("airports")
                .localField("destination.$id")
                .foreignField("_id")
                .as("destinationAirport");
        
        Criteria criteria = new Criteria().andOperator(
//        		Criteria.where("boardingAirport.location.place").is(boardingLocation),
//        		Criteria.where("destinationAirport.location.place").is(destinationLocation),
        		Criteria.where("startTime").gte(startTime)
        		
        		);
        System.out.println(criteria);
        MatchOperation matchOperation = Aggregation.match(criteria);

        Aggregation aggregation = Aggregation.newAggregation(
        		boardingLookupOperation,
        		destinationLookupOperation,
                matchOperation
        );

        AggregationResults<Schedule> aggregate = mongoTemplate.aggregate(aggregation, "schedules", Schedule.class);
		return aggregate
                .getMappedResults();
    }

}
