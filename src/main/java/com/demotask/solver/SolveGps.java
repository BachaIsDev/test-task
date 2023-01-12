package com.demotask.solver;


import com.demotask.entity.GpsPosition;
import com.demotask.parser.Nmea;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Spliterator;

public class SolveGps {

    //class has no instances
    private SolveGps(){};

    //method returns path [km]
    public static double path(String from, String to, File file) throws FileNotFoundException {
        GpsPosition gpsPosition1 = null;
        GpsPosition gpsPosition2 = null;

        Scanner scanner = new Scanner(file);

        while(scanner.hasNext() && (gpsPosition1 == null || gpsPosition2 == null)) {
            Nmea nmea = new Nmea();
            String line = scanner.nextLine();
            System.out.println(line);
            if (nmea.parse(line).getTime() == Double.parseDouble(from)) {
                gpsPosition1 = nmea.parse(line);
            } else if (nmea.parse(line).getTime() == Double.parseDouble(to)) {
                gpsPosition2 = nmea.parse(line);
            }
        }

        return SolveGps.calculatePath(gpsPosition2, gpsPosition1);
    }

    //method calculates path by GPS positions
    static double calculatePath(GpsPosition now, GpsPosition then) {

        if(now == null || then == null){
            System.out.println("Wrong data");
            return 0;
        }

        int earthRadiusKm = 6371;

        double dLat = degreesToRadians(then.getLat() - now.getLat());
        double dLon = degreesToRadians(then.getLon() - now.getLon());
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(now.getLat()) * Math.cos(then.getLat());
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return earthRadiusKm * c;
    }

    static double degreesToRadians(double degrees){
        return degrees * Math.PI / 180;
    }
}
