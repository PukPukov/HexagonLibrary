package ru.ancap.hexagon;

public record FractionalHexagon(HexagonalGrid grid, double q, double r) {
    
    public double s() {
        return -(this.q + this.r);
    }
    
    public Hexagon asStrict() {
        long q = Math.round(this.q);
        long r = Math.round(this.r);
        long s = Math.round(this.s());
        double qDiff = Math.abs(q - this.q);
        double rDiff = Math.abs(r - this.r);
        double sDiff = Math.abs(s - this.s());
        
        if (qDiff > rDiff && qDiff > sDiff) {
            q = -(r + s);
        } else if (rDiff > sDiff) {
            r = -(q + s);
        }
        
        return new Hexagon(this.grid, q, r);
    }
    
}