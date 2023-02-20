package ru.ancap.hexagon.test;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ru.ancap.hexagon.GridOrientation;
import ru.ancap.hexagon.HexagonalGrid;
import ru.ancap.hexagon.common.Point;

import java.util.Random;

public class JavaFXTest extends Application {

    private static final int WIDTH = 1500;
    private static final int HEIGHT = 1000;
    private static final int HEX_SIZE = 30;

    private final HexagonalGrid grid = new HexagonalGrid(GridOrientation.POINTY, new Point(100, 100), new Point(0, 0));

    @Override
    public void start(Stage primaryStage) {

        Group root = new Group();
        Scene scene = new Scene(root, WIDTH, HEIGHT, Color.BLACK);
        primaryStage.setScene(scene);

        int numRectangles = 100000;
        int width = 10;
        int height = 10;
        Color[] colors = { Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.ORANGE, Color.PURPLE };

        for (int i = 0; i < numRectangles; i++) {
            int x = (int)(Math.random() * (scene.getWidth() - width));
            int y = (int)(Math.random() * (scene.getHeight() - height));
            int colorIndex = (int)(Math.random() * colors.length);
            Rectangle rectangle = new Rectangle(x, y, width, height);
            rectangle.setFill(colors[colorIndex]);
            root.getChildren().add(rectangle);
        }

        primaryStage.show();

        int NOISE_RANGE = 20;

        // Create a new image with the specified dimensions
        WritableImage image = scene.snapshot(new WritableImage(WIDTH, HEIGHT));

        // Get the pixel writer for the image
        PixelWriter pixelWriter = image.getPixelWriter();

        // Create a random number generator
        Random random = new Random();

        // Add noise to each pixel in the image
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                // Get the current color at this pixel
                Color currentColor = Color.RED;

                // Add some noise to the color
                double r = currentColor.getRed() + random.nextDouble() * NOISE_RANGE;
                double g = currentColor.getGreen() + random.nextDouble() * NOISE_RANGE;
                double b = currentColor.getBlue() + random.nextDouble() * NOISE_RANGE;

                // Ensure the color values are within the valid range of 0.0 to 1.0
                r = Math.max(Math.min(r, 1.0), 0.0);
                g = Math.max(Math.min(g, 1.0), 0.0);
                b = Math.max(Math.min(b, 1.0), 0.0);

                // Set the new color for this pixel
                pixelWriter.setColor(x, y, new Color(r, g, b, 1.0));
            }
        }

        // Create an ImageView to display the final image
        ImageView imageView = new ImageView(image);

        // Create a new scene with the ImageView as the root node
        Scene scene2 = new Scene(new StackPane(imageView), WIDTH, HEIGHT);

        // Set the stage's scene and show it
        primaryStage.setScene(scene2);
        primaryStage.show();
        
        
        /* Group root = new Group();

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
            for (int i = 0; i < vertexes.size(); i++) {
                Point position = vertexes.get(i).position();
                xPoints[i] = position.x();
                yPoints[i] = position.y();
            }
            gc.fillPolygon(xPoints, yPoints, xPoints.length);
        }
        */
        
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
