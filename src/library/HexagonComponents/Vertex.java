package library.HexagonComponents;

import library.Hexagon;

public class Vertex {

    private Hexagon hexagon;
    private int vertexIndex;

    public Vertex(Hexagon hexagon, int vertexIndex) {
        this.hexagon = hexagon;
        this.vertexIndex = vertexIndex;
    }

    public int getVertexIndex() {
        return this.vertexIndex;
    }

    public Hexagon[] getConnected() {
        Hexagon[] connected = new Hexagon[2];
        if (this.vertexIndex == 0) {
            connected[0] = hexagon.getNeighbor(5);
            connected[1] = hexagon.getNeighbor(0);
        }
        if (this.vertexIndex == 1) {
            connected[0] = hexagon.getNeighbor(0);
            connected[1] = hexagon.getNeighbor(1);
        }
        if (this.vertexIndex == 2) {
            connected[0] = hexagon.getNeighbor(1);
            connected[1] = hexagon.getNeighbor(2);
            return connected;
        }
        if (this.vertexIndex == 3) {
            connected[0] = hexagon.getNeighbor(2);
            connected[1] = hexagon.getNeighbor(3);
            return connected;
        }
        if (this.vertexIndex == 4) {
            connected[0] = hexagon.getNeighbor(3);
            connected[1] = hexagon.getNeighbor(4);
            return connected;
        }
        connected[0] = hexagon.getNeighbor(4);
        connected[1] = hexagon.getNeighbor(5);
        return connected;
    }

    public Vertex[] getEquals() {
        Hexagon[] connected = this.getConnected();
        Vertex[] equals = new Vertex[2];
        if (this.vertexIndex == 0) {
            equals[0] = new Vertex(connected[0], 2);
            equals[1] = new Vertex(connected[1], 4);
            return equals;
        }
        if (this.vertexIndex == 1) {
            equals[0] = new Vertex(connected[0], 3);
            equals[1] = new Vertex(connected[1], 5);
            return equals;
        }
        if (this.vertexIndex == 2) {
            equals[0] = new Vertex(connected[0], 4);
            equals[1] = new Vertex(connected[1], 0);
            return equals;
        }
        if (this.vertexIndex == 3) {
            equals[0] = new Vertex(connected[0], 5);
            equals[1] = new Vertex(connected[1], 1);
            return equals;
        }
        if (this.vertexIndex == 4) {
            equals[0] = new Vertex(connected[0], 0);
            equals[1] = new Vertex(connected[1], 2);
            return equals;
        }
        equals[0] = new Vertex(connected[0], 1);
        equals[1] = new Vertex(connected[1], 3);
        return equals;
    }
    public Vertex getAbsolute() {
        Vertex[] equals = this.getEquals();
        for (Vertex equal : equals) {
            int equalVertexNumber = equal.getVertexIndex();
            if (equalVertexNumber == 0 || equalVertexNumber == 3) {
                return equal;
            }
        }
        return this;
    }
}
