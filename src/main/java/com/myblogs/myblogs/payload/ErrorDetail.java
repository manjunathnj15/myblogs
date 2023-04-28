package com.myblogs.myblogs.payload;

import java.util.Date;

public class ErrorDetail {
    private Date timeStamp;
    private String message;


    private String description;
    public ErrorDetail(Date timeStamp, String message, String descrption) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.description = descrption;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
