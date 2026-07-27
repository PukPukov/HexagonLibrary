package ru.ancap.hexagon.drawer;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import ru.ancap.hexagon.Hexagon;
import ru.ancap.hexagon.HexagonRegion;
import ru.ancap.hexagon.HexagonSide;
import ru.ancap.hexagon.HexagonVertex;
import ru.ancap.hexagon.common.Figure;
import ru.ancap.hexagon.common.Point;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
public class FXCollapsedRegionDrawer {
    
    private static final int WIDTH = 1500;
    private static final int HEIGHT = 1000;
    
    private final Stage primaryStage;
    private final HexagonRegion region;
    
    /**
     * @return GraphicsContext for additional drawing
     */
    public GraphicsContext run() {
        GraphicsContext graphicsContext = FXHexagonLibraryDrawUtils.prepare(WIDTH, HEIGHT, this.primaryStage);
        List<Figure> collapsed = this.region.collapse();
        System.out.println("Hexagons in region: "+ this.region.hexagons());
        System.out.println("Collapsed figure: "+ collapsed);
        for (Figure figure : collapsed) {
            List<Point> vertexes = figure.vertexes();
            double[] xPoints = new double[vertexes.size()];
            double[] yPoints = new double[vertexes.size()];
            int i = 0;
            for (Point vertex : vertexes) {
                xPoints[i] = vertex.x();
                yPoints[i] = vertex.y();
                i++;
            }
            graphicsContext.fillPolygon(xPoints, yPoints, xPoints.length);
        }
        return graphicsContext;
    }
    
}