package com.fbs.customer.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import com.fbs.customer.model.BookingDetails;

@Repository
public class CustomBookingDetailsRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<BookingDetails> findAllBookingsByPnrAndEmail(String pnr, String email) {
    	
    	LookupOperation passengersLookupOperation = LookupOperation.newLookup()
                .from("passengers")
                .localField("passenger.$id")
                .foreignField("_id")
                .as("passengerNew");
    	

        Criteria criteria = new Criteria().andOperator(
                Criteria.where("passengerNew.email").is(email),
                Criteria.where("PNR").is(pnr)
                );

        MatchOperation matchOperation = Aggregation.match(criteria);

        Aggregation aggregation = Aggregation.newAggregation(
                passengersLookupOperation,
                matchOperation
        );

        AggregationResults<BookingDetails> aggregate = mongoTemplate.aggregate(aggregation, "bookingDetails", BookingDetails.class);
        return aggregate.getMappedResults();
    }
    
public List<BookingDetails> findAllBookingsByEmail(String email) {
    	
    	LookupOperation passengersLookupOperation = LookupOperation.newLookup()
                .from("passengers")
                .localField("passenger.$id")
                .foreignField("_id")
                .as("passengerNew");
    	

        Criteria criteria = new Criteria().andOperator(
                Criteria.where("passengerNew.email").is(email)
                );

        MatchOperation matchOperation = Aggregation.match(criteria);

        Aggregation aggregation = Aggregation.newAggregation(
                passengersLookupOperation,
                matchOperation
        );

        AggregationResults<BookingDetails> aggregate = mongoTemplate.aggregate(aggregation, "bookingDetails", BookingDetails.class);
        return aggregate.getMappedResults();
    }
}