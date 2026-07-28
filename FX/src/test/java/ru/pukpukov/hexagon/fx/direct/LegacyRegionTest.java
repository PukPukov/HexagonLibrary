package ru.pukpukov.hexagon.fx.direct;

import javafx.application.Application;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import ru.pukpukov.hexagon.fx.launchers.LegacyRegionTestLauncher;
import ru.pukpukov.commons.debug.HandTest;
import ru.pukpukov.hexagon.core.GridOrientation;
import ru.pukpukov.hexagon.core.HexagonRegion;
import ru.pukpukov.hexagon.core.HexagonalGrid;
import ru.pukpukov.hexagon.core.common.Figure;
import ru.pukpukov.hexagon.core.common.Point;
import ru.pukpukov.hexagon.fx.drawer.FXRegionDrawer;
import ru.pukpukov.hexagon.fx.util.PolygonDrawer;

import java.awt.*;
import java.util.List;

/**
 * Use launcher to launch (workaround of strange javafx bug), link: {@link LegacyRegionTestLauncher}
 */
@HandTest
public class LegacyRegionTest extends Application {
    
    private final HexagonalGrid grid = new HexagonalGrid(GridOrientation.POINTY, new Point(100, 100), new Point(0, 0));
    
    @Override
    public void start(Stage primaryStage) {
        
        Polygon polygon = new Polygon(
            new int[] {500, 500, 700, 700},
            new int[] {500, 700, 700, 500},
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