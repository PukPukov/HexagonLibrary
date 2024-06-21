package ru.ancap.hexagon;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.ancap.algorithm.axis.CyclicNumberAxis;
import ru.ancap.hexagon.common.Point;

import java.util.Set;

@AllArgsConstructor
@EqualsAndHashCode @ToString
public class HexagonVertex {
    
    private final Hexagon baseHexagon;
    private final int vertexIndex;
    
    public int vertexIndex() {
        return this.vertexIndex;
    }
    
    public Set<Hexagon> connected() {
        return Set.of(
            this.baseHexagon,
            this.baseHexagon.neighbor(this.vertexIndex),
            this.baseHexagon.neighbor((int) CyclicNumberAxis.HEXAGONAL.offset(this.vertexIndex, 1))
        );
    }
    
    public HexagonVertex absolute() {
        Set<HexagonVertex> equals = this.equivalents();
        for (HexagonVertex equal : equals) {
            int equalVertexNumber = equal.vertexIndex();
            if (equalVertexNumber == 0 || equalVertexNumber == 3) {
                return equal;
            }
        }
        return this;
    }
    
    private Set<HexagonVertex> equivalents() {
        return Set.of(
            new HexagonVertex(this.baseHexagon.neighbor(this.vertexIndex), (int) CyclicNumberAxis.HEXAGONAL.offset(this.vertexIndex, 2)),
            new HexagonVertex(this.baseHexagon.neighbor((int) CyclicNumberAxis.HEXAGONAL.offset(this.vertexIndex, 1)), (int) CyclicNumberAxis.HEXAGONAL.offset(this.vertexIndex, 4))
        );
    }
    
    public Point position() {
        HexagonalGrid grid = this.baseHexagon.grid();
        Point center = this.baseHexagon.center();
        Point size = grid.size();
        GridOrientation gridOrientation = grid.orientation();
        double x = size.x() * gridOrientation.cosinuses()[this.vertexIndex] + center.x();
        double y = size.y() * gridOrientation.sinuses()[this.vertexIndex] + center.y();
        return new Point(x, y);
    }
    
}