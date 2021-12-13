package library;

import library.HexagonComponents.HexagonSide;
import library.HexagonComponents.HexagonVertex;

public class Hexagon {
    private long q;
    private long r;
    private HexagonalGrid grid;

    public Hexagon(HexagonalGrid grid, long q, long r) {
        this.q = q;
        this.r = r;
        this.grid = grid;
    }

    public Hexagon(Hexagon hexagon) {
        long q = hexagon.getQ();
        long r = hexagon.getR();
        HexagonalGrid grid = hexagon.getGrid();
        this.q = q;
        this.r = r;
        this.grid = grid;
    }

    public long getQ() {
        return q;
    }

    public long getR() {
        return r;
    }

    public long getS() {
        return -(q + r);
    }

    public long getCode() {
        Morton64 mort = grid.getMort();
        return mort.spack(this.q, this.r);
    }

    public HexagonalGrid getGrid() {
        return this.grid;
    }

    public Point getCenter() {
        Orientation orientation = grid.getOrientation();
        Point size = grid.getSize();
        Point origin = grid.getOrigin();
        double x = (orientation.getF()[0] * this.q + orientation.getF()[1] * this.r) * size.getX() + origin.getX();
        double y = (orientation.getF()[2] * this.q + orientation.getF()[3] * this.r) * size.getY() + origin.getY();
        return new Point(x, y);
    }

    public HexagonSide getSide(int index) {
        return new HexagonSide(this, index);
    }

    public HexagonSide[] getSides() {
        HexagonSide[] hexagonSides = new HexagonSide[6];
        for (int i = 0; i<6; i++) {
            hexagonSides[i] = this.getSide(i);
        }
        return hexagonSides;
    }

    public HexagonVertex getVertex(int index) {
        return new HexagonVertex(this, index);
    }

    public HexagonVertex[] getVertexes() {
        HexagonVertex[] hexagonVertices = new HexagonVertex[6];
        for (int i = 0; i<6; i++) {
            hexagonVertices[i] = this.getVertex(i);
        }
        return hexagonVertices;
    }

    public Point getVertexPosition(int index) {
        HexagonVertex hexagonVertex = new HexagonVertex(this, index);
        return hexagonVertex.getPosition();
    }

    public Point[] getVertexPositions() {
        Point[] corners = new Point[6];
        for (int i = 0; i < 6; i++) {
            corners[i] = this.getVertexPosition(i);
        }
        return corners;
    }

    public Hexagon getNeighbor(int index) {
        HexagonalDirection hexagonalDirection = new HexagonalDirection(index);
        return this.getNeighbor(hexagonalDirection);
    }
    public Hexagon getNeighbor(HexagonalDirection hexagonalDirection) {
        int hexagonalDirectionIndex = hexagonalDirection.getIndex();
        if (hexagonalDirectionIndex == 0) {
            return new Hexagon(this.grid, this.q+1, this.r);
        }
        if (hexagonalDirectionIndex == 1) {
            return new Hexagon(this.grid, this.q, this.r+1);
        }
        if (hexagonalDirectionIndex == 2) {
            return new Hexagon(this.grid, this.q-1, this.r+1);
        }
        if (hexagonalDirectionIndex == 3) {
            return new Hexagon(this.grid, this.q-1, this.r);
        }
        if (hexagonalDirectionIndex == 4) {
            return new Hexagon(this.grid, this.q, this.r-1);
        }
        return new Hexagon(this.grid, this.q+1, this.r-1);
    }
    public Hexagon[] getNeighbors() {
        Hexagon[] hexagons = new Hexagon[6];
        for (int i = 0; i<6; i++) {
            hexagons[i] = getNeighbor(i);
        }
        return hexagons;
    }
    public Hexagon[] getNeighbors(int layers) {
        int total = (layers + 1) * layers * 3;
        Hexagon[] neighbors = new Hexagon[total];
        int i = 0;
        for (long q = -layers; q <= layers; q++) {
            long r1 = Math.max(-layers, -q - layers);
            long r2 = Math.min(layers, -q + layers);
            for (long r = r1; r <= r2; r++) {
                if (q == 0 && r == 0) {
                    continue;
                }
                neighbors[i] = new Hexagon(this.grid, q + this.q, r + this.r);
                i++;
            }
        }
        return neighbors;
    }

    @Override
    public String toString() {
        return String.format("hexagon{q: %d, r: %d}", q, r);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Hexagon.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        Hexagon other = (Hexagon)obj;

        return other.q == q && other.r == r;
    }
}
