package library;

import java.util.ArrayList;
import java.util.HashMap;

public class Region {
    HexagonalGrid grid;
    private Hexagon[] hexagones;
    private HashMap<Long, Integer> lookup;

    Region(HexagonalGrid grid, Point[] geometry) {
        this.grid = grid;

        int len = geometry.length;
        if (geometry[0] == geometry[len - 1]) {
            len -= 1;
        }

        Hexagon hexagon = grid.getHexagon(geometry[0]);
        long q1 = hexagon.getQ();
        long q2 = hexagon.getQ();
        long r1 = hexagon.getR();
        long r2 = hexagon.getR();

        for (int i = 1; i < len; i++) {
            hexagon = grid.getHexagon(geometry[i]);
            q1 = Math.min(q1, hexagon.getQ());
            q2 = Math.max(q2, hexagon.getQ());
            r1 = Math.min(r1, hexagon.getR());
            r2 = Math.max(r2, hexagon.getR());
        }

        q1 -= 1;
        q2 += 1;
        r1 -= 1;
        r2 += 1;

        ArrayList<Hexagon> hexagones = new ArrayList<>();

        for (long q = q1; q <= q2; q++) {
            for (long r = r1; r <= r2; r++) {
                hexagon = new Hexagon(grid, q, r);
                Point[] corners = hexagon.getVertexPositions();
                boolean add = false;

                for (int c = 0; c < 6; c++) {
                    if (pointInGeometry(geometry, len, corners[c])) {
                        add = true;
                        break;
                    }
                }

                if (!add) {
                    for (int i = 0; i < len; i++) {
                        if (pointInGeometry(corners, 6, geometry[i])) {
                            add = true;
                            break;
                        }
                    }
                }

                if (add) {
                    hexagones.add(hexagon);
                }
            }
        }

        this.hexagones = hexagones.toArray(new Hexagon[0]);

        lookup = new HashMap<>();
        for (int i = 0; i < this.hexagones.length; i++) {
            lookup.put(this.hexagones[i].getCode(), i);
        }
    }

    public Hexagon[] getHexagones() {
        return hexagones;
    }

    public boolean contains(Hexagon hexagon) {
        return lookup.containsKey(hexagon.getCode());
    }

    private boolean pointInGeometry(Point[] geometry, int len, Point point) {
        boolean contains = intersectsWithRaycast(point, geometry[len - 1], geometry[0]);
        for (int i = 1; i < len; i++) {
            if (intersectsWithRaycast(point, geometry[i - 1], geometry[i])) {
                contains = !contains;
            }
        }
        return contains;
    }

    private boolean intersectsWithRaycast(Point point, Point start, Point end) {
        if (start.getY() > end.getY()) {
            return intersectsWithRaycast(point, end, start);
        }

        while (point.getY() == start.getY() || point.getY() == end.getY()) {
            double newY = Math.nextAfter(point.getY(), Double.POSITIVE_INFINITY);
            point = new Point(point.getX(), newY);
        }

        if (point.getY() < start.getY() || point.getY() > end.getY()) {
            return false;
        }

        if (start.getX() > end.getX()) {
            if (point.getX() > start.getX()) {
                return false;
            }
            if (point.getX() < end.getX()) {
                return true;
            }
        } else {
            if (point.getX() > end.getX()) {
                return false;
            }
            if (point.getX() < start.getX()) {
                return true;
            }
        }

        double raySlope = (point.getY() - start.getY()) / (point.getX() - start.getX());
        double diagSlope = (end.getY() - start.getY()) / (end.getX() - start.getX());

        return raySlope >= diagSlope;
    }
}
