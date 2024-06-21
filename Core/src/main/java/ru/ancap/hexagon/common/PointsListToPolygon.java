package ru.ancap.hexagon.common;

import java.awt.*;
import java.util.List;
import java.util.function.Function;

public class PointsListToPolygon implements Function<List<Point>, Polygon> {
    
    public static PointsListToPolygon INSTANCE = new PointsListToPolygon();
    
    /* Shouldn't instantiate, should be used by provided instance */
    private PointsListToPolygon() {}
    
    @Override
    public Polygon apply(List<Point> points) {
        int pointsAmount = points.size();
        int[] xPoints = new int[pointsAmount];
        int[] yPoints = new int[pointsAmount];
        for (int i = 0; i < pointsAmount; i++) {
            xPoints[i] = (int) points.get(i).x();
            yPoints[i] = (int) points.get(i).y();
        }
        return new Polygon(xPoints, yPoints, pointsAmount);
    }
    
}