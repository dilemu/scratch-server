package com.example.server.model.dto;

public class BodyDTO<T> {
    private T body_parts;
    private T locations;

    public T getBody_parts() {
        return body_parts;
    }

    public void setBody_parts(T body_parts) {
        this.body_parts = body_parts;
    }

    public T getLocations() {
        return locations;
    }

    public void setLocations(T locations) {
        this.locations = locations;
    }
}
