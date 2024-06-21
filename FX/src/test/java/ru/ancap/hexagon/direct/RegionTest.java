package ru.ancap.hexagon.direct;

import javafx.application.Application;
import javafx.stage.Stage;
import launchers.RegionTestLauncher;
import ru.ancap.commons.debug.HandTest;
import ru.ancap.hexagon.GridOrientation;
import ru.ancap.hexagon.HexagonRegion;
import ru.ancap.hexagon.HexagonalGrid;
import ru.ancap.hexagon.common.Figure;
import ru.ancap.hexagon.common.Point;
import ru.ancap.hexagon.drawer.FXRegionDrawer;

import java.util.List;

/**
 * Use launcher to launch (workaround of strange javafx bug), link: {@link RegionTestLauncher}
 */
@HandTest
public class RegionTest extends Application {

    private final HexagonalGrid grid = new HexagonalGrid(GridOrientation.FLAT, new Point(100, 100), new Point(0, 0));

    @Override
    public void start(Stage primaryStage) {
        Figure figure = new Figure(List.of(
            new Point(400, 400),
            new Point(400, 800),
            new Point(800, 800),
            new Point(800, 400)
        ));
        
        HexagonRegion region = this.grid.regionByIntersection(figure);

        new FXRegionDrawer(primaryStage, region).run();
    }

    public static void main(String[] args) {
        launch(args);
    }

}