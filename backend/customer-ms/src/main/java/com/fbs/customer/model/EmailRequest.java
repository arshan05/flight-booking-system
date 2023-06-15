package com.fbs.customer.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailRequest {

    private String to;
    private String subject;
    private String body;

}

