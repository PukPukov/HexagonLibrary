package ru.ancap.hexagon.drawer;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FXHexagonLibraryDrawUtils {
    
    static GraphicsContext prepare(int width, int height, Stage primaryStage) {
        Group root = new Group();
        
        Canvas canvas = new Canvas(1500, 1500);
        
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        
        Scene scene = new Scene(root, width, height);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        graphicsContext.setFill(Color.PAPAYAWHIP);
        return graphicsContext;
    }
    
}