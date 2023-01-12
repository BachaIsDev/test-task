package com.demotask.parser;


import com.demotask.entity.GpsPosition;

import java.util.HashMap;
import java.util.Map;


public class Nmea {

    private static final Map<String, SentenceParser> sentenceParsers = new HashMap<>();

    public Nmea() {
        sentenceParsers.put("GPGGA", new GPGGA());
        sentenceParsers.put("GNVTG", new GNVTG());
    }

    static double Latitude(String lat, String NS) {
        if(lat.equals("") || NS.equals("")){
            return 0;
        }
        double value = Double.parseDouble(lat.substring(2))/60.0f;
        value +=  Double.parseDouble(lat.substring(0, 2));
        if(NS.startsWith("S")) {
            value = -value;
        }
        return value;
    }

    static double Longitude(String lon, String WE) {
        if(lon.equals("") || WE.equals("")){
            return 0;
        }
        double value = Double.parseDouble(lon.substring(3))/60.0f;
        value +=  Double.parseDouble(lon.substring(0, 3));
        if(WE.startsWith("W")) {
            value = -value;
        }
        return value;
    }

    // parsers
    //inner classes for parsing different commands
    class GPGGA implements SentenceParser {
        public boolean parse(String [] tokens, GpsPosition position) {
            if(tokens.length != 15){
                return false;
            }
            position.setTime(Double.parseDouble(tokens[1]));
            position.setLat(Latitude(tokens[2], tokens[3]));
            position.setLon(Longitude(tokens[4], tokens[5]));
            return true;
        }
    }

    class GNVTG implements SentenceParser {
        public boolean parse(String [] tokens, GpsPosition position){
            if(tokens.length != 10){
                return false;
            }
            position.setVelocity(Double.parseDouble(tokens[7]));
            return true;
        }
    }

    public GpsPosition parse(String line) {
        GpsPosition position = new GpsPosition();

        if(line.startsWith("$")) {
            String nmea = line.substring(1);
            String[] tokens = nmea.split(",");
            String type = tokens[0];
            //TODO check crc
            if(sentenceParsers.containsKey(type)) {
                sentenceParsers.get(type).parse(tokens, position);
            }
        }

        return position;
    }
}

