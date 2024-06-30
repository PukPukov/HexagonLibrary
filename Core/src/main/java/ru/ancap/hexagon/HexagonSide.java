package ru.ancap.hexagon;

import ru.ancap.algorithm.axis.CyclicNumberAxis;
import ru.ancap.commons.Pair;

import java.util.List;

/**
 * @param base Hexagon that technically contains this side
 */
public record HexagonSide(Hexagon base, int direction) {
    
    /**
     * @return Hexagons, that actually contains this side. Key is base hexagon, value is connected. 
     */
    public List<Hexagon> connected() {
        return List.of(this.base, this.base.neighbor(this.direction()));
    }
    
    public List<HexagonVertex> ends() {
        return List.of(this.start(), this.end());
    }
    
    public HexagonVertex start() {
        return this.base.vertex((int) CyclicNumberAxis.HEXAGONAL.offset(this.direction, 0));
    }
    
    public HexagonVertex end() {
        return this.base.vertex((int) CyclicNumberAxis.HEXAGONAL.offset(this.direction, 1));
    }
    
    /**
     * Actually, when hexagons are on grid, they share same sides with neighbors. But technically
     * one side is attached to only one hexagon, so there is equivalents, and equivalent can be obtained
     * with that method.
     */
    public HexagonSide equal() {
        return new HexagonSide(this.base.neighbor(this.direction()), (int) CyclicNumberAxis.HEXAGONAL.offset(this.direction, 3));
    }
    
    /**
     * Actually, when hexagons are on grid, they share same sides with neighbors. But technically
     * one side is attached to only one hexagon, so there is equivalents, and if you need absolute representation
     * of sides, you can get rid of equivalents inducing absolute representation.
     */
    public HexagonSide absolute() {
        if (this.direction > 2)
            return new HexagonSide(this.base.neighbor(this.direction), (int) CyclicNumberAxis.HEXAGONAL.offset(this.direction, -3));
        else return this;
    }
    
}