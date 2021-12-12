package library;

import library.HexagonComponents.Side;

import java.util.ArrayList;

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

    public Side[] getBounds(Hexagon[] hexagons) {
        ArrayList<Side> sides = new ArrayList<>();
        for (int i = 0; i<hexagons.length; i++) {
            Side[] hexagonSides = hexagons[i].getSides();
            for (int j = 0; i<hexagonSides.length; i++) {
                hexagonSides[i] = hexagonSides[i].getAbsolute();
                sides.add(hexagonSides[i]);
            }
        }
        for(int i=0; i<sides.size(); i++) {
            for (int j=i+1; j<sides.size(); j++) {
                if(sides.get(i).equals(sides.get(j))) {
                    sides.remove(i);
                    sides.remove(j);
                }
            }
        }
        return sides.toArray(new Side[0]);
    }

    public Region createRegion(Point[] geometry) {
        return new Region(this, geometry);
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
