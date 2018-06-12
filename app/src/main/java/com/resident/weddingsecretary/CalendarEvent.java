package com.resident.weddingsecretary;

/**
 * Created by resident on 12.06.18.
 */

public class CalendarEvent {
    private String type;
    private Long time;
    private String address;
    private String comment;

    public CalendarEvent(String type, Long time, String address, String comment) {
        this.type = type;
        this.time = time;
        this.address = address;
        this.comment = comment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
