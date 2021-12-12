package library.HexagonComponents;

import library.Hexagon;
import library.HexagonalDirection;
import library.Point;

public class Side {

    private Hexagon baseHexagon;
    private int sideIndex;

    public Side(Hexagon baseHexagon, int sideIndex) {
        this.baseHexagon = baseHexagon;
        this.sideIndex = sideIndex;
    }

    public Hexagon getBaseHexagon() {
        return baseHexagon;
    }

    public int getIndex() {
        return this.sideIndex;
    }

    public HexagonalDirection getHexagonalDirection() {
        return new HexagonalDirection(this.getIndex());
    }

    public Hexagon getConnected() {
        HexagonalDirection hexagonalDirection = this.getHexagonalDirection();
        return baseHexagon.getNeighbor(hexagonalDirection);
    }

    public Point[] getEnds() {
        Point[] points = new Point[2];
        points[0] = new Vertex(this.getBaseHexagon(), this.getIndex()+1).getPosition();
        points[1] = new Vertex(this.getBaseHexagon(), this.getIndex()+1).getPosition();
        return points;
    }

    public Side getEqual() {
        if (this.sideIndex == 0) {
            return new Side(this.baseHexagon, 3);
        }
        if (this.sideIndex == 1) {
            return new Side(this.baseHexagon, 4);
        }
        if (this.sideIndex == 2) {
            return new Side(this.baseHexagon, 5);
        }
        if (this.sideIndex == 3) {
            return new Side(this.baseHexagon, 0);
        }
        if (this.sideIndex == 4) {
            return new Side(this.baseHexagon, 1);
        }
        return new Side(this.baseHexagon, 2);
    }

    public Side getAbsolute() {
        if (this.sideIndex < 3) {
            if (this.sideIndex == 3) {
                return new Side(this.baseHexagon.getNeighbor(3), 0);
            }
            if (this.sideIndex == 4) {
                return new Side(this.baseHexagon.getNeighbor(4), 1);
            }
            if (this.sideIndex == 4) {
                return new Side(this.baseHexagon.getNeighbor(5), 2);
            }
        }
        return this;
    }

    @Override
    public String toString() {
        return String.format("side{baseHexagon: %s, sideIndex: %i}", this.baseHexagon.toString(), this.sideIndex);
    }

    public boolean equals(Side other) {
        if (other == null) {
            return false;
        }
        return other.getBaseHexagon().equals(this.baseHexagon) && other.getIndex() == this.sideIndex;
    }
}
