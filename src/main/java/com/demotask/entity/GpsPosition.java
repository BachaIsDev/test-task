package com.demotask.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class GpsPosition {
    private double time = 0;
    private double lat = 0;
    private double lon = 0;
    private double velocity = 0;


    public String toString() {
        return String.format("POSITION: lat: %f, lon: %f, time: %f, alt: %f, vel: %f", lat, lon, time, velocity);
    }
}
