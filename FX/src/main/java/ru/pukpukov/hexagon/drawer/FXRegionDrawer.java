package ru.pukpukov.hexagon.drawer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import ru.pukpukov.hexagon.Hexagon;
import ru.pukpukov.hexagon.HexagonRegion;
import ru.pukpukov.hexagon.HexagonSide;
import ru.pukpukov.hexagon.HexagonVertex;
import ru.pukpukov.hexagon.common.Point;

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
            var start = side.start();
            var end = side.end();
            graphicsContext.strokeLine(
                start.position().x(), start.position().y(),
                end.position().x(),   end.position().y()
            );
        }
        return graphicsContext;
    }
    
}