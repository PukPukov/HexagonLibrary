package ru.pukpukov.hexagon;

import org.junit.jupiter.api.Test;
import ru.pukpukov.hexagon.core.*;
import ru.pukpukov.hexagon.core.common.Figure;
import ru.pukpukov.hexagon.core.common.Point;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HexagonalGridTest {
    
    private final HexagonalGrid FLAT = new HexagonalGrid(GridOrientation.FLAT, new Point(20, 10), new Point(10, 20));
    private final HexagonalGrid POINTY = new HexagonalGrid(GridOrientation.POINTY, new Point(20, 10), new Point(10, 20));
    
    @Test
    public void testFlat() {
        HexagonalGrid grid = FLAT;
        assertEquals(new Hexagon(grid,   0,  37), grid.hexagon(new Point(  13,  666)));
        assertEquals(new Hexagon(grid,  22, -11), grid.hexagon(new Point( 666,   13)));
        assertEquals(new Hexagon(grid,  -1, -39), grid.hexagon(new Point( -13, -666)));
        assertEquals(new Hexagon(grid, -22,   9), grid.hexagon(new Point(-666,  -13)));
    }
    
    @Test
    public void testPointy() {
        HexagonalGrid grid = POINTY;
        assertEquals(new Hexagon(grid, -21, 43 ), grid.hexagon(new Point(13, 666)));
        assertEquals(new Hexagon(grid,  19,  0 ), grid.hexagon(new Point(666, 13)));
        assertEquals(new Hexagon(grid,  22, -46), grid.hexagon(new Point(-13, -666)));
        assertEquals(new Hexagon(grid, -19, -2 ), grid.hexagon(new Point(-666, -13)));
    }
    
    private void validatePoint(Point e, Point r, double precision) {
        assertEquals(e.x(), r.x(), precision);
        assertEquals(e.y(), r.y(), precision);
    }
    
    @Test
    public void testCoordinatesFlat() {
        Hexagon hex = FLAT.hexagon(new Point(666, 666));
        validatePoint(new Point(670.00000, 660.85880), hex.center(), 0.00001);
        Point[] expectedCorners = new Point[]{
            new Point(690.00000, 660.85880),
            new Point(680.00000, 669.51905),
            new Point(660.00000, 669.51905),
            new Point(650.00000, 660.85880),
            new Point(660.00000, 652.19854),
            new Point(680.00000, 652.19854)};
        Point[] corners = hex.vertexes().stream().map(HexagonVertex::position).toArray(Point[]::new);
        for (int i = 0; i < 6; i++) {
            validatePoint(expectedCorners[i], corners[i], 0.00001);
        }
    }
    
    @Test
    public void testCoordinatesPointy() {
        Hexagon hex = POINTY.hexagon(new Point(666, 666));
        validatePoint(new Point(650.85880, 665.00000), hex.center(), 0.00001);
        Point[] expectedCorners = new Point[]{
            new Point(668.17930, 670.00000),
            new Point(650.85880, 675.00000),
            new Point(633.53829, 670.00000),
            new Point(633.53829, 660.00000),
            new Point(650.85880, 655.00000),
            new Point(668.17930, 660.00000)};
        Point[] corners = hex.vertexes().stream().map(HexagonVertex::position).toArray(Point[]::new);
        for (int i = 0; i < 6; i++) {
            validatePoint(expectedCorners[i], corners[i], 0.00001);
        }
    }
    
    @Test
    public void testNeighbors() {
        Hexagon hex = FLAT.hexagon(new Point(666, 666));
        Set<Long> expectedNeighbors = Set.of(920L, 922L, 944L, 915L, 921L, 923L, 945L, 916L, 918L,
            926L, 948L, 917L, 919L, 925L, 927L, 960L, 962L, 968L);
        Set<Long> neighbors = hex.neighbors(2).stream().map(Hexagon::code).collect(Collectors.toSet());
        assertEquals(expectedNeighbors, neighbors);
    }
    
    @Test
    public void testRegion() {
        Point[] geometry = new Point[]{
            new Point(20, 19.99999), new Point(20, 40), new Point(40, 60),
            new Point(60, 40), new Point(50, 30), new Point(40, 40)};
        HexagonRegion region = FLAT.regionByIntersection(new Figure(Arrays.asList(geometry)));
        Set<Long> hexagonCodes = region.hexagons().stream().map(Hexagon::code).collect(Collectors.toSet());
        Set<Long> expectedHexagonCodes = Set.of(0L, 2L, 1L, 3L, 9L, 4L);
        assertEquals(expectedHexagonCodes, hexagonCodes);
    }
    
}