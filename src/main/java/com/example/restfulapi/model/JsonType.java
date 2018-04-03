package com.example.restfulapi.model;

public class JsonType {

    private String message;
    private String status;

    public JsonType(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public JsonType() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "JsonType{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
