package ru.ancap.hexagon;

import ru.ancap.algorithm.axis.CyclicNumberAxis;
import ru.ancap.commons.Pair;

public record HexagonSide(Hexagon base, int direction) {
    
    public Pair<Hexagon, Hexagon> connected() {
        return new Pair<>(
            this.base,
            this.base.neighbor(this.direction())
        );
    }
    
    public Pair<HexagonVertex, HexagonVertex> ends() {
        return new Pair<>(
            this.base.vertex((int) CyclicNumberAxis.HEXAGONAL.offset(this.direction, 0)),
            this.base.vertex((int) CyclicNumberAxis.HEXAGONAL.offset(this.direction, 1))
        );
    }
    
    public HexagonSide equal() {
        return new HexagonSide(this.base.neighbor(this.direction()), (int) CyclicNumberAxis.HEXAGONAL.offset(this.direction, 3));
    }
    
    public HexagonSide absolute() {
        if (this.direction > 2)
            return new HexagonSide(this.base.neighbor(this.direction), (int) CyclicNumberAxis.HEXAGONAL.offset(this.direction, -3));
        else return this;
    }
    
}