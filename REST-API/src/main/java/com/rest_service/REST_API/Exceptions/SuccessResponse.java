package com.rest_service.REST_API.Exceptions;

import java.time.LocalDateTime;

public class SuccessResponse {
    private LocalDateTime timeStamp;
    private String message;

    public SuccessResponse(LocalDateTime timeStamp, String message) {
        this.timeStamp = timeStamp;
        this.message = message;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
