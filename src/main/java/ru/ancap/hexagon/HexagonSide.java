package ru.ancap.hexagon;

import javafx.util.Pair;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.ancap.hexagon.common.CyclicNumberAxis;

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
            this.baseHexagon.vertex(CyclicNumberAxis.HEXAGONAL.offset(this.direction, 0)),
            this.baseHexagon.vertex(CyclicNumberAxis.HEXAGONAL.offset(this.direction, 1))
        );
    }

    public HexagonSide equal() {
        return new HexagonSide(this.baseHexagon.neighbor(this.direction()), CyclicNumberAxis.HEXAGONAL.offset(this.direction, 3));
    }

    public HexagonSide absolute() {
        if (this.direction > 2) return new HexagonSide(this.baseHexagon.neighbor(this.direction), CyclicNumberAxis.HEXAGONAL.offset(this.direction, -3));
        else return this;
    }

}
