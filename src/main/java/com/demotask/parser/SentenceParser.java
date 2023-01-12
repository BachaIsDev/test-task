package com.demotask.parser;


import com.demotask.entity.GpsPosition;

public interface SentenceParser {
    boolean parse(String [] tokens, GpsPosition position);
}
