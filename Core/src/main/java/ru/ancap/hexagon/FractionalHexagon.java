package ru.ancap.hexagon;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode @ToString
public class FractionalHexagon {
    
    private HexagonalGrid grid;
    private double q;
    private double r;
    
    public HexagonalGrid grid() {return this.grid;}
    public double q() {return this.q;}
    public double r() {return this.r;}
    public double s() {return -(this.q + this.r);}
    
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