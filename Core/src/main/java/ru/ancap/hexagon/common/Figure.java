package ru.ancap.hexagon.common;

import java.awt.*;
import java.util.List;

public record Figure(List<Point> vertexes) {
    
    public Polygon toPolygon() {
        return PointsListToPolygon.INSTANCE.apply(this.vertexes());
    }
    
}