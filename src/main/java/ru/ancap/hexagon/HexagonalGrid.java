package ru.ancap.hexagon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.ancap.hexagon.HexagonComponents.HexagonSide;

import java.util.ArrayList;
import java.util.Collections;

@Getter
@AllArgsConstructor
public class HexagonalGrid {
    private ru.ancap.hexagon.Orientation orientation;
    private Point origin;
    private Point size;
    private Morton64 mort;

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

    public Hexagon getHexagon(long code) {
        long[] qr = mort.sunpack(code);
        return new Hexagon(this, qr[0], qr[1]);
    }

    public Hexagon getHexagon(String code) {
        String[] positions = code.split(";");
        return new Hexagon(this, Long.parseLong(positions[0]), Long.parseLong(positions[1]));
    }

    public Hexagon getHexagon(Point point) {
        double x = (point.getX() - this.origin.getX()) / this.size.getX();
        double y = (point.getY() - this.origin.getY()) / this.size.getY();
        double q = this.orientation.getB()[0] * x + this.orientation.getB()[1] * y;
        double r = this.orientation.getB()[2] * x + this.orientation.getB()[3] * y;
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
    
}
