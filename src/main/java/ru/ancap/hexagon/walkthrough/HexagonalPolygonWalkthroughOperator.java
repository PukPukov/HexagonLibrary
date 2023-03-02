package ru.ancap.hexagon.walkthrough;

import ru.ancap.algorithm.walkthrough.classic.RegionWalkthroughOperator;
import ru.ancap.hexagon.Hexagon;
import ru.ancap.hexagon.common.Point;

import java.awt.*;

public class HexagonalPolygonWalkthroughOperator extends RegionWalkthroughOperator<Hexagon> {
    
    public HexagonalPolygonWalkthroughOperator(Polygon polygon) {
        super((hexagon) -> {
            Point center = hexagon.center();
            return polygon.contains(center.x(), center.y());
        });
    }
    
}
