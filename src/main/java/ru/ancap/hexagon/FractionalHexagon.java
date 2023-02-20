package ru.ancap.hexagon;

public class FractionalHexagon {
    private double q;
    private double r;
    private HexagonalGrid grid;

    public FractionalHexagon(double q, double r, HexagonalGrid grid) {
        this.q = q;
        this.r = r;
        this.grid = grid;
    }

    public double getQ() {
        return q;
    }

    public double getR() {
        return r;
    }

    public double getS() {
        return -(q + r);
    }

    public Hexagon toHexagon() {
        long q = Math.round(getQ());
        long r = Math.round(getR());
        long s = Math.round(getS());
        double qDiff = Math.abs(q - getQ());
        double rDiff = Math.abs(r - getR());
        double sDiff = Math.abs(s - getS());

        if (qDiff > rDiff && qDiff > sDiff) {
            q = -(r + s);
        } else if (rDiff > sDiff) {
            r = -(q + s);
        }

        return new Hexagon(grid, q, r);
    }

    @Override
    public String toString() {
        return String.format("fraction_hexagon{q: %d, r: %d}", q, r);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!FractionalHexagon.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        FractionalHexagon other = (FractionalHexagon)obj;

        return other.q == q && other.r == r;
    }
}
