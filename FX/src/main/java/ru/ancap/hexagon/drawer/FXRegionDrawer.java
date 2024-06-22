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
import ru.ancap.hexagon.common.Point;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
public class FXRegionDrawer {
    
    private static final int WIDTH = 1500;
    private static final int HEIGHT = 1000;
    
    private final Stage primaryStage;
    private final HexagonRegion region;
    
    /**
     * @return GraphicsContext for additional drawing
     */
    public GraphicsContext run() {
        GraphicsContext graphicsContext = FXHexagonLibraryDrawUtils.prepare(WIDTH, HEIGHT, this.primaryStage);
        for (Hexagon hex : this.region.hexagons()) {
            List<HexagonVertex> vertexes = hex.vertexes();
            double[] xPoints = new double[vertexes.size()];
            double[] yPoints = new double[vertexes.size()];
            int i = 0;
            for (HexagonVertex vertex : vertexes) {
                Point position = vertex.position();
                xPoints[i] = position.x();
                yPoints[i] = position.y();
                i++;
            }
            graphicsContext.fillPolygon(xPoints, yPoints, xPoints.length);
        }
        
        Set<HexagonSide> sides = this.region.bounds();
        graphicsContext.setLineWidth(5);
        graphicsContext.setStroke(Color.BLACK);
        
        for (HexagonSide side : sides) {
            var pair = side.ends();
            graphicsContext.strokeLine(
                pair.key().position().x(),   pair.key().position().y(),
                pair.value().position().x(), pair.value().position().y()
            );
        }
        return graphicsContext;
    }
    
}