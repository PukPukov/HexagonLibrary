package library;

import library.HexagonComponents.HexagonSide;

import java.util.ArrayList;
import java.util.Collections;

public class HexagonalGrid {
    private Orientation orientation;
    private Point origin;
    private Point size;
    private Morton64 mort;

    public HexagonalGrid(Orientation orientation, Point origin, Point size, Morton64 mort) {
        this.orientation = orientation;
        this.origin = origin;
        this.size = size;
        this.mort = mort;
    }

    public HexagonalGrid(HexagonalGrid hexagonalGrid) {
        Orientation orientation = hexagonalGrid.getOrientation();
        Point origin = hexagonalGrid.getOrigin();
        Point size = hexagonalGrid.getSize();
        Morton64 mort = hexagonalGrid.getMort();
        this.orientation = orientation;
        this.origin = origin;
        this.size = size;
        this.mort = mort;
    }

    public Morton64 getMort() {
        return mort;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Point getOrigin() {
        return origin;
    }

    public Point getSize() {
        return size;
    }

    public Hexagon getHexagon(long code) {
        long[] qr = mort.sunpack(code);
        return new Hexagon(this, qr[0], qr[1]);
    }

    public Hexagon getHexagon(Point point) {
        double x = (point.getX() - origin.getX()) / size.getX();
        double y = (point.getY() - origin.getY()) / size.getY();
        double q = orientation.getB()[0] * x + orientation.getB()[1] * y;
        double r = orientation.getB()[2] * x + orientation.getB()[3] * y;
        return (new FractionalHexagon(q, r, this)).toHexagon();
    }

    public HexagonSide[] getBounds(Hexagon[] hexagons) {
        ArrayList<HexagonSide> sides = new ArrayList<>();
        for (int i = 0; i<hexagons.length; i++) {
            HexagonSide[] hexagonSides = hexagons[i].getSides();
            for (int j = 0; j<hexagonSides.length; j++) {
                hexagonSides[j] = hexagonSides[j].getAbsolute();
                sides.add(hexagonSides[j]);
            }
        }
        for(int i=0; i<sides.size(); i++) {
            for (int j=i+1; j<sides.size(); j++) {
                if (sides.get(i) == null || sides.get(j) == null) {
                    continue;
                }
                if(sides.get(i).equals(sides.get(j))) {
                    sides.set(i, null);
                    sides.set(j, null);
                }
            }
        }
        sides.removeAll(Collections.singleton(null));
        return sides.toArray(new HexagonSide[0]);
    }

    public HexagonRegion createRegion(Point[] geometry) {
        return new HexagonRegion(this, geometry);
    }

    @Override
    public String toString() {
        return String.format("hexagongrid{orientation: %s, origin: %s, size: %s, mort: %s}", orientation.toString(), origin.toString(), size.toString(), mort.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!HexagonalGrid.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        HexagonalGrid other = (HexagonalGrid)obj;
        return other.orientation.equals(orientation) && other.origin.equals(origin) && other.size.equals(size) && other.mort.equals(mort);
    }
}
