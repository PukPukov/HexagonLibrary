package ru.ancap.hexagon.test;

/* import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ru.ancap.hexagon.*;
import ru.ancap.hexagon.common.Figure;
import ru.ancap.hexagon.common.Point;

import java.util.List;
import java.util.Set; */

public class RegionTest /* extends Application */ {

    /* private static final int WIDTH = 1500;
    private static final int HEIGHT = 1000;

    private final HexagonalGrid grid = new HexagonalGrid(GridOrientation.FLAT, new Point(100, 100), new Point(0, 0));

    @Override
    public void start(Stage primaryStage) {
        
        Group root = new Group();

        System.out.println(1);
        
        HexagonRegion region = this.grid.region(new Figure(List.of(
            new Point(500, 500),
            new Point(500, 700),
            new Point(700, 700),
            new Point(700, 500)
        )));

        System.out.println(2);

        Canvas canvas = new Canvas(1500, 1500);

        // get the graphics context
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        root.getChildren().add(canvas);
        
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        gc.setFill(Color.PAPAYAWHIP);
        for (Hexagon hex : region.hexagons()) {
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
            gc.fillPolygon(xPoints, yPoints, xPoints.length);
        }
        
        Set<HexagonSide> sides = region.bounds();
        gc.setLineWidth(5);
        gc.setStroke(Color.BLACK);
        
        for (HexagonSide side : sides) {
            var pair = side.ends();
            gc.strokeLine(
                pair.getKey().position().x(),   pair.getKey().position().y(), 
                pair.getValue().position().x(), pair.getValue().position().y()
            );
        }
        
    }

    public static void main(String[] args) {
        launch(args);
    } */
    
}
