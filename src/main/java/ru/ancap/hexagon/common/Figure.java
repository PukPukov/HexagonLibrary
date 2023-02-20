package ru.ancap.hexagon.common;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Figure {
    
    private final List<Point> vertexes;
    
    public List<Point> vertexes() {return this.vertexes;}
    
}
