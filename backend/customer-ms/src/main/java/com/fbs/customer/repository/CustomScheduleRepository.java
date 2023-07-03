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

    public List<Schedule> findFlightSchedules(String boardingLocation, String destinationLocation, Date startTime) {
    	
    	LookupOperation boardingLookupOperation = LookupOperation.newLookup()
                .from("airports")
                .localField("boarding.$id")
                .foreignField("_id")
                .as("boardingAirport");
    	
    	LookupOperation boardingLocationLookupOperation = LookupOperation.newLookup()
                .from("locations")
                .localField("boardingAirport.location.$id")
                .foreignField("_id")
                .as("boardingLocation");
        


        LookupOperation destinationLookupOperation = LookupOperation.newLookup()
                .from("airports")
                .localField("destination.$id")
                .foreignField("_id")
                .as("destinationAirport");
        
        LookupOperation destinationLocationLookupOperation = LookupOperation.newLookup()
                .from("locations")
                .localField("destinationAirport.location.$id")
                .foreignField("_id")
                .as("destinationLocation");
        

        Criteria criteria = new Criteria().andOperator(
                Criteria.where("boardingLocation.place").is(boardingLocation),
                Criteria.where("destinationLocation.place").is(destinationLocation),
                Criteria.where("startTime").gte(startTime)
        );

        MatchOperation matchOperation = Aggregation.match(criteria);

        Aggregation aggregation = Aggregation.newAggregation(
                boardingLookupOperation,
                boardingLocationLookupOperation,
                destinationLookupOperation,
                destinationLocationLookupOperation,
                matchOperation
        );

        AggregationResults<Schedule> aggregate = mongoTemplate.aggregate(aggregation, "schedules", Schedule.class);
        return aggregate.getMappedResults();
    }
}