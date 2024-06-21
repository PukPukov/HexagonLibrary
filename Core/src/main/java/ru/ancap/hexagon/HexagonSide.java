package ru.ancap.hexagon;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.ancap.algorithm.axis.CyclicNumberAxis;
import ru.ancap.commons.Pair;

@AllArgsConstructor
@EqualsAndHashCode @ToString
public class HexagonSide {
    
    private final Hexagon baseHexagon;
    private final int direction;
    
    public int direction() {return this.direction;}
    public Hexagon base() {return this.baseHexagon;}
    
    public Pair<Hexagon, Hexagon> connected() {
        return new Pair<>(
            this.baseHexagon,
            this.baseHexagon.neighbor(this.direction())
        );
    }
    
    public Pair<HexagonVertex, HexagonVertex> ends() {
        return new Pair<>(
            this.baseHexagon.vertex((int) CyclicNumberAxis.HEXAGONAL.offset(this.direction, 0)),
            this.baseHexagon.vertex((int) CyclicNumberAxis.HEXAGONAL.offset(this.direction, 1))
        );
    }
    
    public HexagonSide equal() {
        return new HexagonSide(this.baseHexagon.neighbor(this.direction()), (int) CyclicNumberAxis.HEXAGONAL.offset(this.direction, 3));
    }
    
    public HexagonSide absolute() {
        if (this.direction > 2) return new HexagonSide(this.baseHexagon.neighbor(this.direction), (int) CyclicNumberAxis.HEXAGONAL.offset(this.direction, -3));
        else return this;
    }
    
}