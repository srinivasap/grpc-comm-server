package com.mobile.sensor.comm.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Srinivasa Prasad Sunnapu
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage {

    @JsonProperty("message")
    private String message;


    public ErrorMessage() {

    }

    public ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
