package com.example.whatsapp.Model;

public class message  {

    String uid, meessage;
    Long timestamp;

    public message(String uid, String meessage, Long timestamp) {
        this.uid = uid;
        this.meessage = meessage;
        this.timestamp = timestamp;
    }

    public message(String uid, String meessage) {
        this.uid = uid;
        this.meessage = meessage;
    }

    public message() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMeessage() {
        return meessage;
    }

    public void setMeessage(String meessage) {
        this.meessage = meessage;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
