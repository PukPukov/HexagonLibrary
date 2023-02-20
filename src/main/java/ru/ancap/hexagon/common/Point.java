package ru.ancap.hexagon.common;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode @ToString
public class Point {
    
    private final double x;
    private final double y;

    public double x() {return x;}
    public double y() {return y;}
    
}
