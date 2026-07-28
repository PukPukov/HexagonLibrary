package ru.pukpukov.hexagon.fx.util;

import javafx.scene.canvas.GraphicsContext;
import lombok.AllArgsConstructor;

import java.awt.*;

@AllArgsConstructor
public class PolygonDrawer implements Runnable {
    
    private final GraphicsContext graphicsContext;
    private final Polygon polygon;
    
    @Override
    public void run() {
        int pointsAmount = this.polygon.npoints;
        double[] xPoints = new double[pointsAmount]; for (int i = 0; i < pointsAmount; i++) xPoints[i] = this.polygon.xpoints[i];
        double[] yPoints = new double[pointsAmount]; for (int i = 0; i < pointsAmount; i++) yPoints[i] = this.polygon.ypoints[i];
        this.graphicsContext.strokePolygon(xPoints, yPoints, pointsAmount);
    }
    
}