package library.HexagonComponents;

import library.Hexagon;
import library.HexagonalGrid;
import library.Orientation;
import library.Point;

public class HexagonVertex {

    private Hexagon baseHexagon;
    private int vertexIndex;

    public HexagonVertex(Hexagon hexagon, int vertexIndex) {
        this.baseHexagon = hexagon;
        this.vertexIndex = vertexIndex;
    }

    public int getVertexIndex() {
        return this.vertexIndex;
    }

    public Hexagon[] getConnected() {
        Hexagon[] connected = new Hexagon[2];
        if (this.vertexIndex == 0) {
            connected[0] = baseHexagon.getNeighbor(5);
            connected[1] = baseHexagon.getNeighbor(0);
        }
        if (this.vertexIndex == 1) {
            connected[0] = baseHexagon.getNeighbor(0);
            connected[1] = baseHexagon.getNeighbor(1);
        }
        if (this.vertexIndex == 2) {
            connected[0] = baseHexagon.getNeighbor(1);
            connected[1] = baseHexagon.getNeighbor(2);
            return connected;
        }
        if (this.vertexIndex == 3) {
            connected[0] = baseHexagon.getNeighbor(2);
            connected[1] = baseHexagon.getNeighbor(3);
            return connected;
        }
        if (this.vertexIndex == 4) {
            connected[0] = baseHexagon.getNeighbor(3);
            connected[1] = baseHexagon.getNeighbor(4);
            return connected;
        }
        connected[0] = baseHexagon.getNeighbor(4);
        connected[1] = baseHexagon.getNeighbor(5);
        return connected;
    }

    public HexagonVertex[] getEquals() {
        Hexagon[] connected = this.getConnected();
        HexagonVertex[] equals = new HexagonVertex[2];
        if (this.vertexIndex == 0) {
            equals[0] = new HexagonVertex(connected[0], 2);
            equals[1] = new HexagonVertex(connected[1], 4);
            return equals;
        }
        if (this.vertexIndex == 1) {
            equals[0] = new HexagonVertex(connected[0], 3);
            equals[1] = new HexagonVertex(connected[1], 5);
            return equals;
        }
        if (this.vertexIndex == 2) {
            equals[0] = new HexagonVertex(connected[0], 4);
            equals[1] = new HexagonVertex(connected[1], 0);
            return equals;
        }
        if (this.vertexIndex == 3) {
            equals[0] = new HexagonVertex(connected[0], 5);
            equals[1] = new HexagonVertex(connected[1], 1);
            return equals;
        }
        if (this.vertexIndex == 4) {
            equals[0] = new HexagonVertex(connected[0], 0);
            equals[1] = new HexagonVertex(connected[1], 2);
            return equals;
        }
        equals[0] = new HexagonVertex(connected[0], 1);
        equals[1] = new HexagonVertex(connected[1], 3);
        return equals;
    }

    public HexagonVertex getAbsolute() {
        HexagonVertex[] equals = this.getEquals();
        for (HexagonVertex equal : equals) {
            int equalVertexNumber = equal.getVertexIndex();
            if (equalVertexNumber == 0 || equalVertexNumber == 3) {
                return equal;
            }
        }
        return this;
    }

    public Point getPosition() {
        HexagonalGrid grid = baseHexagon.getGrid();
        Point center = baseHexagon.getCenter();
        Point size = grid.getSize();
        Orientation orientation = grid.getOrientation();
        double x = size.getX() * orientation.getCosinuses()[this.vertexIndex] + center.getX();
        double y = size.getY() * orientation.getSinuses()[this.vertexIndex] + center.getY();
        Point vertexPosition = new Point(x, y);
        return vertexPosition;
    }
}
