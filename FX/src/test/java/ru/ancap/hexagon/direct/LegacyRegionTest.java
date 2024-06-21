package ru.ancap.hexagon.direct;

import javafx.application.Application;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import launchers.LegacyRegionTestLauncher;
import ru.ancap.hexagon.GridOrientation;
import ru.ancap.hexagon.HexagonRegion;
import ru.ancap.hexagon.HexagonalGrid;
import ru.ancap.hexagon.common.Figure;
import ru.ancap.hexagon.common.Point;
import ru.ancap.hexagon.drawer.FXRegionDrawer;
import ru.ancap.hexagon.lib.PolygonDrawer;

import java.awt.*;
import java.util.List;

/**
 * Use launcher to launch (workaround of strange javafx bug), link: {@link LegacyRegionTestLauncher}
 */
public class LegacyRegionTest extends Application {

    private final HexagonalGrid grid = new HexagonalGrid(GridOrientation.FLAT, new Point(100, 100), new Point(0, 0));

    @Override
    public void start(Stage primaryStage) {

        Polygon polygon = new Polygon(
            new int[]{500, 500, 700, 700},
            new int[]{500, 700, 700, 500},
            4
        );
        
        HexagonRegion region = this.grid.regionByIntersection(new Figure(List.of(
            new Point(500, 500),
            new Point(500, 700),
            new Point(700, 700),
            new Point(700, 500)
        )));
        
        GraphicsContext graphicsContext = new FXRegionDrawer(primaryStage, region).run();
        new PolygonDrawer(graphicsContext, polygon).run();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}