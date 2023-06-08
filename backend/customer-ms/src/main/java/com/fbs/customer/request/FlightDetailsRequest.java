package com.fbs.customer.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FlightDetailsRequest {
    private String start;
    private String destination;
    private Date date;

}
